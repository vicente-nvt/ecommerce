package com.ecommerce.aplicacao.itemdopedido;

import com.ecommerce.aplicacao.base.ObjetoDto;s
import com.ecommerce.dominio.entidades.ItemDoPedido;

public class ItemDoPedidoDto implements ObjetoDto<ItemDoPedido> {

    private long id;
    private long idDoProduto;
    private int quantidade;
    private long idDoPedido;

    public ItemDoPedidoDto(long id, long idDoPedido, long idDoProduto, int quantidade) {
        this.id = id;
        this.idDoPedido = idDoPedido;
        this.idDoProduto = idDoProduto;
        this.quantidade = quantidade;
    }

    public long getIdDoProduto() {
        return this.idDoProduto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public long getIdDoPedido() {
        return this.idDoPedido;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }
}