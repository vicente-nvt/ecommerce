package com.ecommerce.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.ClienteDto;
import com.ecommerce.aplicacao.cliente.ConsultorDeCliente;
import com.ecommerce.aplicacao.cliente.CriadorDeCliente;
import com.ecommerce.aplicacao.cliente.EditorDeCliente;
import com.ecommerce.aplicacao.cliente.RemovedorDeCliente;
import com.ecommerce.dominio.entidades.Cliente;

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
@RequestMapping("/rest/clientes")
public class ClienteController extends ControllerBase<Cliente>{

    @Autowired
    public ClienteController(CriadorDeCliente criadorDeCliente, 
        ConsultorDeCliente consultorDeCliente,
        EditorDeCliente editorDeCliente,
        RemovedorDeCliente removedorDeCliente) {
        super(criadorDeCliente, consultorDeCliente, editorDeCliente, removedorDeCliente);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody ClienteDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto<Cliente>> doGet(@PathVariable long id) {
        return executarGet(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody ClienteDto dto, @PathVariable long id) {
        return executarPut(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }
    
}