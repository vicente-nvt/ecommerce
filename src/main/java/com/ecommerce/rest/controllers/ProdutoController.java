package com.ecommerce.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.produto.ConsultorDeProduto;
import com.ecommerce.aplicacao.produto.CriadorDeProduto;
import com.ecommerce.aplicacao.produto.EditorDeProduto;
import com.ecommerce.aplicacao.produto.ProdutoDto;
import com.ecommerce.aplicacao.produto.RemovedorDeProduto;
import com.ecommerce.dominio.entidades.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/produtos")
public class ProdutoController extends ControllerBase<Produto> {

    @Autowired
    public ProdutoController(CriadorDeProduto criadorDeProduto, ConsultorDeProduto consultorDeProduto,
            EditorDeProduto editorDeProduto, RemovedorDeProduto removedorDeProduto) {
        super(criadorDeProduto, consultorDeProduto, editorDeProduto, removedorDeProduto);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody ProdutoDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

    @GetMapping("/")
    public ResponseEntity<List<ObjetoDto<Produto>>> doGet() {
        return obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto<Produto>> doGet(@PathVariable long id) {
        return executarGet(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody ProdutoDto dto, @PathVariable long id) {
        return executarPut(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }
}