package com.ecommerce.aplicacao.pedido;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.ClienteParaConsultaDePedidoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultaDeItemDoPedidoDto;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class ConsultaDePedido implements ConsultorDePedido {

    private PedidoRepositorio repositorio;

    @Autowired
    public ConsultaDePedido(PedidoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ObjetoDto<Pedido> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException {
        return mapearPedido(obterPor(id));
    }

    private ConsultaDePedidoDto mapearPedido(Pedido pedido) {
        return new ConsultaDePedidoDto(
            pedido.getId(), 
            mapearClienteParaDto(pedido.getCliente()), 
            pedido.getData(),
            pedido.getStatus(),
            mapearItens(pedido.getItens()));
    }

    private Collection<ConsultaDeItemDoPedidoDto> mapearItens(Collection<ItemDoPedido> itens) {
        return itens.stream().map(item -> mapearItemParaDto(item)).collect(Collectors.toList());
    }

    private ConsultaDeItemDoPedidoDto mapearItemParaDto(ItemDoPedido item) {
        return new ConsultaDeItemDoPedidoDto(item.getId(), item.getProduto().getId(), item.getProduto().getNome(),
                item.getValor(), item.getQuantidade(), item.getSubtotal());
    }

    private ClienteParaConsultaDePedidoDto mapearClienteParaDto(Cliente cliente) {
        return new ClienteParaConsultaDePedidoDto(
            cliente.getId(),
            cliente.getNome(), 
            cliente.getEmail()
        );
    }

    @Override
    public Pedido obterPor(long id) throws NotFoundException {
        Pedido pedido = repositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        return pedido;
    }

    @Override
    public List<ObjetoDto<Pedido>> consultarTodos() {
        return repositorio.findAll().stream()
            .map(pedido -> mapearPedido(pedido))
            .collect(Collectors.toList());
    }
}