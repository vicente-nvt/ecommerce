package com.ecommerce.rest.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.Consultor;
import com.ecommerce.aplicacao.base.Criador;
import com.ecommerce.aplicacao.base.Editor;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.base.Removedor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javassist.NotFoundException;

public class ControllerBase<T> {

    private Criador criador;
    private Editor editor;
    private Removedor removedor;
    private Consultor consultor;

    public ControllerBase(Criador criador, Consultor consultor, Editor editor, Removedor removedor) {
        this.criador = criador;
        this.editor = editor;
        this.removedor = removedor;
        this.consultor = consultor;
    }

    public ResponseEntity<Object> executarPost(ObjetoDto<T> dto, HttpServletRequest request) {

        long idDoObjeto;
        try {
            idDoObjeto = criador.criar(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idDoObjeto)
            .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("Location", location.toString());

        ResponseEntity<Object> resposta = new ResponseEntity<Object>(responseHeaders, HttpStatus.CREATED);
        return resposta;
    }

    public ResponseEntity<ObjetoDto<T>> executarGet(long id) {
        ObjetoDto<T> objeto;
        try {
             objeto = consultor.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return new ResponseEntity<ObjetoDto<T>>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<ObjetoDto<T>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ObjetoDto<T>>(objeto , HttpStatus.OK);
    }

    public ResponseEntity<Object> executarPut(ObjetoDto<T> dto, long id) {

        dto.setId(id);
        try {
            editor.editar(dto);
        } catch (ExcecaoDeAplicacao e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> executarDelete(long id) {

        try {
            removedor.remover(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ExcecaoDeAplicacao e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}