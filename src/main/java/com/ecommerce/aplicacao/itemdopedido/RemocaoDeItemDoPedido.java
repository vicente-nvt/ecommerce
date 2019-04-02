package com.ecommerce.aplicacao.itemdopedido;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class RemocaoDeItemDoPedido implements RemovedorDeItemDoPedido {

    private ConsultorDeItemDoPedido consultorDeItem;
    private ItemDoPedidoRepositorio repositorio;

    public RemocaoDeItemDoPedido(ItemDoPedidoRepositorio repositorio, ConsultorDeItemDoPedido consultorDeItem) {
        this.repositorio = repositorio;
        this.consultorDeItem = consultorDeItem;
	}

	@Override
    public void remover(long id) throws ExcecaoDeAplicacao, NotFoundException {
        ItemDoPedido item = consultorDeItem.obterPor(id);

        repositorio.delete(item);
    }
}