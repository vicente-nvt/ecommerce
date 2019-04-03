package com.ecommerce.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.produto.CadastroDeProdutoDto;
import com.ecommerce.aplicacao.produto.ConsultorDeProduto;
import com.ecommerce.aplicacao.produto.CriadorDeProduto;
import com.ecommerce.aplicacao.produto.EditorDeProduto;
import com.ecommerce.aplicacao.produto.ProdutoDto;
import com.ecommerce.aplicacao.produto.RemovedorDeProduto;
import com.ecommerce.dominio.entidades.Produto;

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
@RequestMapping("/rest/produtos")
public class ProdutoController extends ControllerBase<Produto> {

    private ConsultorDeProduto consultorDeProduto;

    @Autowired
    public ProdutoController(CriadorDeProduto criadorDeProduto, ConsultorDeProduto consultorDeProduto,
            EditorDeProduto editorDeProduto, RemovedorDeProduto removedorDeProduto) {
        super(criadorDeProduto, editorDeProduto, removedorDeProduto);
        this.consultorDeProduto = consultorDeProduto;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CadastroDeProdutoDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody CadastroDeProdutoDto dto, @PathVariable long id) {
        return executarPut(dto, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> doGet(@PathVariable long id) {
        ObjetoDto<Produto> produto;
        try {
            produto = consultorDeProduto.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<ProdutoDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().body((ProdutoDto) produto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProdutoDto>> doGet() {
        List<ObjetoDto<Produto>> listaDeObjetos;
        try {
            listaDeObjetos = consultorDeProduto.consultarTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
        List<ProdutoDto> listaDeProdutos = new ArrayList<ProdutoDto>();
        listaDeObjetos.stream().forEach(produto -> listaDeProdutos.add((ProdutoDto) produto));
        return ResponseEntity.ok().body(listaDeProdutos);

    }
}