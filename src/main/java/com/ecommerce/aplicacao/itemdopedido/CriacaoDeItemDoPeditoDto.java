package com.ecommerce.aplicacao.itemdopedido;

import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.dominio.entidades.ItemDoPedido;

public class CriacaoDeItemDoPeditoDto implements CadastroDto<ItemDoPedido> {

    private long id;
    private long idDoProduto;
    private int quantidade;
    private long idDoPedido;

    public CriacaoDeItemDoPeditoDto(long id, long idDoPedido, long idDoProduto, int quantidade) {
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

    public void setIdDoPedido(long idDoPedido) {
        this.idDoPedido = idDoPedido;
    }
}