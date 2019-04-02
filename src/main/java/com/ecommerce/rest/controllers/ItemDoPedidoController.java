package com.ecommerce.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultorDeItemDoPedido;
import com.ecommerce.aplicacao.itemdopedido.CriacaoDeItemDoPeditoDto;
import com.ecommerce.aplicacao.itemdopedido.CriadorDeItemDoPedido;
import com.ecommerce.aplicacao.itemdopedido.RemovedorDeItemDoPedido;
import com.ecommerce.dominio.entidades.ItemDoPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/pedidos/{idDoPedido}/itens")
public class ItemDoPedidoController extends ControllerBase<ItemDoPedido> {

    @Autowired
    public ItemDoPedidoController(CriadorDeItemDoPedido criadorDeItemDoPedido,
            ConsultorDeItemDoPedido consultorDeItemDoPedido, RemovedorDeItemDoPedido removedorDeItemDoPedido) {
        super(criadorDeItemDoPedido, consultorDeItemDoPedido, null, removedorDeItemDoPedido);
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CriacaoDeItemDoPeditoDto dto, @PathVariable long idDoPedido,
            HttpServletRequest request) {
        dto.setIdDoPedido(idDoPedido);
        return executarPost(dto, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto<ItemDoPedido>> doGet(@PathVariable long id) {
        return executarGet(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }
}