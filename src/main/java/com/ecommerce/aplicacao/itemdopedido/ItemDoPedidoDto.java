package com.ecommerce.aplicacao.itemdopedido;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.produto.ProdutoDto;
import com.ecommerce.dominio.entidades.ItemDoPedido;

public class ItemDoPedidoDto implements ObjetoDto<ItemDoPedido> {

    private long id;
    private ProdutoDto produto;
    private int quantidade;
    private BigDecimal valor;

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }
}