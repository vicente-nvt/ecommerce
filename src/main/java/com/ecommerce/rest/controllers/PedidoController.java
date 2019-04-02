package com.ecommerce.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.pedido.CriadorDePedido;
import com.ecommerce.aplicacao.pedido.FechadorDePedido;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.pedido.ConsultorDePedido;
import com.ecommerce.aplicacao.pedido.CriacaoDePedidoDto;
import com.ecommerce.dominio.entidades.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/rest/pedidos")
public class PedidoController extends ControllerBase<Pedido> {

    private FechadorDePedido fechadorDePedido;

    @Autowired
    public PedidoController(CriadorDePedido criadorDePedido, ConsultorDePedido consultorDePedido, FechadorDePedido fechadorDePedido) {
        super(criadorDePedido, consultorDePedido, null, null);
        this.fechadorDePedido = fechadorDePedido;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CriacaoDePedidoDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<Object> doPut(@PathVariable long id) {
        try {
            fechadorDePedido.fechar(id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto<Pedido>> doGet(@PathVariable long id) {
        return executarGet(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<ObjetoDto<Pedido>>> doGet() {
        return obterTodos();
    }

}   