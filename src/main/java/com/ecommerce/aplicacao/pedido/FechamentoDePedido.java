package com.ecommerce.aplicacao.pedido;

import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class FechamentoDePedido implements FechadorDePedido {

    private PedidoRepositorio repositorio;
    private ConsultorDePedido consultorDePedido;

    public FechamentoDePedido(PedidoRepositorio repositorio, ConsultorDePedido consultorDePedido) {
        this.repositorio = repositorio;
        this.consultorDePedido = consultorDePedido;
    }

    @Override
    public void fechar(long id) throws NotFoundException {
        Pedido pedido = consultorDePedido.obterPor(id);

        pedido.fechar();

        repositorio.save(pedido);
    }
}