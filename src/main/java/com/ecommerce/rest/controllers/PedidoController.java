package com.ecommerce.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.pedido.ConsultaDePedidoDto;
import com.ecommerce.aplicacao.pedido.ConsultorDePedido;
import com.ecommerce.aplicacao.pedido.CadastroDePedidoDto;
import com.ecommerce.aplicacao.pedido.CriadorDePedido;
import com.ecommerce.aplicacao.pedido.FechadorDePedido;
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
    private ConsultorDePedido consultorDePedido;


    @Autowired
    public PedidoController(CriadorDePedido criadorDePedido, ConsultorDePedido consultorDePedido, FechadorDePedido fechadorDePedido) {
        super(criadorDePedido, null, null);
        this.consultorDePedido = consultorDePedido;
        this.fechadorDePedido = fechadorDePedido;
    }

    @PostMapping("/")
    public ResponseEntity<Object> doPost(@RequestBody CadastroDePedidoDto dto, HttpServletRequest request) {
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
    public ResponseEntity<ConsultaDePedidoDto> doGet(@PathVariable long id) {
        ObjetoDto<Pedido> pedido;
        try {
            pedido = consultorDePedido.consultarPor(id);
        } catch (ExcecaoDeAplicacao e) {
            return new ResponseEntity<ConsultaDePedidoDto>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<ConsultaDePedidoDto>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<ConsultaDePedidoDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ConsultaDePedidoDto>((ConsultaDePedidoDto) pedido, HttpStatus.OK);
   }

    @GetMapping("/")
    public ResponseEntity<List<ConsultaDePedidoDto>> doGet() {
        List<ObjetoDto<Pedido>> listaDeObjetos;
        try {
            listaDeObjetos = consultorDePedido.consultarTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
        List<ConsultaDePedidoDto> listaDePedidos = new ArrayList<ConsultaDePedidoDto>();
        listaDeObjetos.stream().forEach(pedido -> listaDePedidos.add((ConsultaDePedidoDto) pedido));
        return ResponseEntity.ok().body(listaDePedidos);
    }

}