package com.ecommerce.aplicacao.pedido;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.aplicacao.cliente.ConsultorDeCliente;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class CriacaoDePedido implements CriadorDePedido {

    private PedidoRepositorio repositorio;
    private ConsultorDeCliente consultorDeCliente;

    @Autowired
    public CriacaoDePedido(PedidoRepositorio repositorio, ConsultorDeCliente consultorDeCliente) {
        this.repositorio = repositorio;
        this.consultorDeCliente = consultorDeCliente;
    }

    @Override
    public long criar(CadastroDto<Pedido> dto) throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        CadastroDePedidoDto pedidoDto = (CadastroDePedidoDto) dto;

        Cliente cliente = consultorDeCliente.obterPor(pedidoDto.getIdDoCliente());

        Pedido novoPedido = new Pedido(cliente);

        Pedido pedidoCriado = repositorio.save(novoPedido);
        return pedidoCriado.getId();
    }
}