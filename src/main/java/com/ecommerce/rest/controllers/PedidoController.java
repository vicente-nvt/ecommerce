package com.ecommerce.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.pedido.CriadorDePedido;
import com.ecommerce.aplicacao.pedido.PedidoDto;
import com.ecommerce.dominio.entidades.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/pedidos")
public class PedidoController extends ControllerBase<Pedido> {

    @Autowired
    public PedidoController(CriadorDePedido criadorDePedido) {
        super(criadorDePedido, null, null, null);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody PedidoDto dto, HttpServletRequest request) {
        return executarPost(dto, request);
    }

}