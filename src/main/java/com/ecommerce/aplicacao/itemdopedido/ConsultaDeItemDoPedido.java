package com.ecommerce.aplicacao.itemdopedido;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class ConsultaDeItemDoPedido implements ConsultorDeItemDoPedido {

    private ItemDoPedidoRepositorio repositorio;

    @Autowired
    public ConsultaDeItemDoPedido(ItemDoPedidoRepositorio repositorio) {
        this.repositorio = repositorio;
	}

	@Override
    public ObjetoDto<ItemDoPedido> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException {
        return mapearItemDoPedidoDto(obterPor(id));
    }

    private ObjetoDto<ItemDoPedido> mapearItemDoPedidoDto(ItemDoPedido item) {
        Produto produto = item.getProduto();

        return new ConsultaDeItemDoPedidoDto(
            item.getId(),
            produto.getId(),
            produto.getNome(),
            item.getValor(),
            item.getQuantidade(), 
            item.getSubtotal()
        );
    }

    @Override
    public ItemDoPedido obterPor(long id) throws NotFoundException {
        ItemDoPedido item = repositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Item não encontrado"));
        
        return item;
    }

    @Override
    public List<ObjetoDto<ItemDoPedido>> consultarTodos() {
        return repositorio.findAll().stream()
            .map(item -> mapearItemDoPedidoDto(item))
            .collect(Collectors.toList());
    }
}