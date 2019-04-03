package com.ecommerce.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.CadastroDeCategoriaDto;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultorDeCategoria;
import com.ecommerce.aplicacao.categoria.CriadorDeCategoria;
import com.ecommerce.aplicacao.categoria.EditorDeCategoria;
import com.ecommerce.aplicacao.categoria.RemovedorDeCategoria;
import com.ecommerce.dominio.entidades.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/rest/categorias")
public class CategoriaController extends ControllerBase<Categoria> {

    private ConsultorDeCategoria consultorDeCategoria;

    @Autowired
    public CategoriaController(CriadorDeCategoria criadorDeCategoria, ConsultorDeCategoria consultorDeCategoria,
        EditorDeCategoria editorDeCategoria, RemovedorDeCategoria removedorDeCategoria) {
            super(criadorDeCategoria, editorDeCategoria, removedorDeCategoria);
            this.consultorDeCategoria = consultorDeCategoria;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CadastroDeCategoriaDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody CadastroDeCategoriaDto dto, @PathVariable long id){
        return executarPut(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> doGet(@PathVariable long id) {
        ObjetoDto<Categoria> categoria;
        try {
            categoria = consultorDeCategoria.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<CategoriaDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().body((CategoriaDto) categoria);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoriaDto>> doGet() {
        List<ObjetoDto<Categoria>> listaDeObjetos;
        try {
            listaDeObjetos = consultorDeCategoria.consultarTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
        List<CategoriaDto> listaDeCategorias = new ArrayList<CategoriaDto>();
        listaDeObjetos.stream().forEach(categoria -> listaDeCategorias.add((CategoriaDto) categoria));
        return ResponseEntity.ok().body(listaDeCategorias);
    }
}