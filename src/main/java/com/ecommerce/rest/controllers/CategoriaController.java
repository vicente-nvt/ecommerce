package com.ecommerce.rest.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ConsultorDeCategoria;
import com.ecommerce.aplicacao.base.CriadorDeCategoria;
import com.ecommerce.aplicacao.dtos.CategoriaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javassist.NotFoundException;

@Controller
@RequestMapping(path = "/rest/categorias")
public class CategoriaController {

    private CriadorDeCategoria criadorDeCategoria;
    private ConsultorDeCategoria consultorDeCategoria;

    @Autowired
    public CategoriaController(CriadorDeCategoria criadorDeCategoria, ConsultorDeCategoria consultorDeCategoria) {
        this.criadorDeCategoria = criadorDeCategoria;
        this.consultorDeCategoria = consultorDeCategoria;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CategoriaDto dto, HttpServletRequest request) {

        long idDaCategoria;
        try {
            idDaCategoria = criadorDeCategoria.criar(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idDaCategoria)
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("Location", location.toString());

        ResponseEntity<Object> resposta = new ResponseEntity<Object>(responseHeaders, HttpStatus.CREATED);
        return resposta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> doGet(@PathVariable("id") long id) {

        CategoriaDto categoria;
        try {
             categoria = (CategoriaDto) consultorDeCategoria.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return new ResponseEntity<CategoriaDto>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<CategoriaDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CategoriaDto>(categoria , HttpStatus.OK);
    }
}