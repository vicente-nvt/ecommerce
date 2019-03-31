package com.ecommerce.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.produto.CriadorDeProduto;
import com.ecommerce.aplicacao.produto.ProdutoDto;
import com.ecommerce.dominio.entidades.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/produtos")
public class ProdutoController extends ControllerBase<Produto> {

    @Autowired
    public ProdutoController(CriadorDeProduto criadorDeProduto){
        super(criadorDeProduto, null, null, null);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody ProdutoDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }
}