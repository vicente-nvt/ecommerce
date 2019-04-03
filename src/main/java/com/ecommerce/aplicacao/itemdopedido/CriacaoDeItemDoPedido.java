package com.ecommerce.aplicacao.itemdopedido;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.aplicacao.pedido.ConsultorDePedido;
import com.ecommerce.aplicacao.produto.ConsultorDeProduto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class CriacaoDeItemDoPedido implements CriadorDeItemDoPedido {

    private ItemDoPedidoRepositorio repositorio;
    private ConsultorDePedido consultorDePedido;
    private ConsultorDeProduto consultorDeProduto;

    @Autowired
    public CriacaoDeItemDoPedido(ItemDoPedidoRepositorio repositorio, ConsultorDePedido consultorDePedido,
            ConsultorDeProduto consultorDeProduto) {
        this.repositorio = repositorio;
        this.consultorDePedido = consultorDePedido;
        this.consultorDeProduto = consultorDeProduto;
    }

    @Override
    public long criar(CadastroDto<ItemDoPedido> dto) throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        CriacaoDeItemDoPeditoDto itemDoPedidoDto = (CriacaoDeItemDoPeditoDto) dto;

        Produto produto = consultorDeProduto.obterPor(itemDoPedidoDto.getIdDoProduto());
        Pedido pedido = consultorDePedido.obterPor(itemDoPedidoDto.getIdDoPedido());

        ItemDoPedido itemDoPedido = new ItemDoPedido(pedido, produto, itemDoPedidoDto.getQuantidade());

        ItemDoPedido itemCriado = repositorio.save(itemDoPedido);
        return itemCriado.getId();
    }
}