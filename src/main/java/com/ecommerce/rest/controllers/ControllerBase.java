package com.ecommerce.rest.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.aplicacao.base.Criador;
import com.ecommerce.aplicacao.base.Editor;
import com.ecommerce.aplicacao.base.Removedor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javassist.NotFoundException;

public class ControllerBase<T> {

    private Criador criador;
    private Editor editor;
    private Removedor removedor;

    public ControllerBase(Criador criador, Editor editor, Removedor removedor) {
        this.criador = criador;
        this.editor = editor;
        this.removedor = removedor;
    }

    public ResponseEntity<Object> executarPost(CadastroDto<T> dto, HttpServletRequest request) {

        long idDoObjeto;
        try {
            idDoObjeto = criador.criar(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idDoObjeto)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> executarPut(CadastroDto<T> dto, long id) {

        try {
            editor.editar(id, dto);
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> executarDelete(long id) {

        try {
            removedor.remover(id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}