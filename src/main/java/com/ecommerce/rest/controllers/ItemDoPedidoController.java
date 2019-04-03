package com.ecommerce.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultaDeItemDoPedidoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultorDeItemDoPedido;
import com.ecommerce.aplicacao.itemdopedido.CriacaoDeItemDoPeditoDto;
import com.ecommerce.aplicacao.itemdopedido.CriadorDeItemDoPedido;
import com.ecommerce.aplicacao.itemdopedido.RemovedorDeItemDoPedido;
import com.ecommerce.dominio.entidades.ItemDoPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/rest/pedidos/{idDoPedido}/itens")
public class ItemDoPedidoController extends ControllerBase<ItemDoPedido> {

    private ConsultorDeItemDoPedido consultorDeItemDoPedido;

    @Autowired
    public ItemDoPedidoController(CriadorDeItemDoPedido criadorDeItemDoPedido,
            ConsultorDeItemDoPedido consultorDeItemDoPedido, RemovedorDeItemDoPedido removedorDeItemDoPedido) {
        super(criadorDeItemDoPedido, null, removedorDeItemDoPedido);
        this.consultorDeItemDoPedido = consultorDeItemDoPedido;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CriacaoDeItemDoPeditoDto dto, @PathVariable long idDoPedido,
            HttpServletRequest request) {
        return executarPost(dto, request);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> doDelete(@PathVariable long id) {
        return executarDelete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDeItemDoPedidoDto> doGet(@PathVariable long id) {
        ObjetoDto<ItemDoPedido> item;
        try {
            item = consultorDeItemDoPedido.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<ConsultaDeItemDoPedidoDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok().body((ConsultaDeItemDoPedidoDto) item);
   }
}