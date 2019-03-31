package com.ecommerce.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultorDeCategoria;
import com.ecommerce.aplicacao.categoria.CriadorDeCategoria;
import com.ecommerce.aplicacao.categoria.EditorDeCategoria;
import com.ecommerce.aplicacao.categoria.RemovedorDeCategoria;
import com.ecommerce.dominio.entidades.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rest/categorias")
public class CategoriaController extends ControllerBase<Categoria> {

    @Autowired
    public CategoriaController(CriadorDeCategoria criadorDeCategoria, ConsultorDeCategoria consultorDeCategoria,
        EditorDeCategoria editorDeCategoria, RemovedorDeCategoria removedorDeCategoria) {
        super(criadorDeCategoria, consultorDeCategoria, editorDeCategoria, removedorDeCategoria);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CategoriaDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody CategoriaDto dto, @PathVariable long id){
        return executarPut(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto<Categoria>> doGet(@PathVariable long id) {
        return executarGet(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<ObjetoDto<Categoria>>> doGet() {
        return obterTodos();
    }
}