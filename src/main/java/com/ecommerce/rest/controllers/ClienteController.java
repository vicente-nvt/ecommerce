package com.ecommerce.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.CadastroDeClienteDto;
import com.ecommerce.aplicacao.cliente.ClienteDto;
import com.ecommerce.aplicacao.cliente.ConsultorDeCliente;
import com.ecommerce.aplicacao.cliente.CriadorDeCliente;
import com.ecommerce.aplicacao.cliente.EditorDeCliente;
import com.ecommerce.aplicacao.cliente.RemovedorDeCliente;
import com.ecommerce.dominio.entidades.Cliente;

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
@RequestMapping("/rest/clientes")
public class ClienteController extends ControllerBase<Cliente> {

    ConsultorDeCliente consultorDeCliente;

    @Autowired
    public ClienteController(CriadorDeCliente criadorDeCliente, ConsultorDeCliente consultorDeCliente,
            EditorDeCliente editorDeCliente, RemovedorDeCliente removedorDeCliente) {
        super(criadorDeCliente, editorDeCliente, removedorDeCliente);
        this.consultorDeCliente = consultorDeCliente;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CadastroDeClienteDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> doPut(@RequestBody CadastroDeClienteDto dto, @PathVariable long id) {
        return executarPut(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> doGet(@PathVariable long id) {
        ObjetoDto<Cliente> objeto;
        
        try {
            objeto = consultorDeCliente.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<ClienteDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().body((ClienteDto) objeto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteDto>> doGet() {
        List<ObjetoDto<Cliente>> listaDeObjetos;
        try {
            listaDeObjetos = consultorDeCliente.consultarTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        List<ClienteDto> listaDeClientes = new ArrayList<ClienteDto>();
        listaDeObjetos.stream().forEach(cliente -> listaDeClientes.add((ClienteDto) cliente));
        return ResponseEntity.ok().body(listaDeClientes);
    }
}