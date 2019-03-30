package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Produto;

public class ItemDoPedidoBuilder {

    private Produto produto;
    private int quantidade;

    public ItemDoPedidoBuilder() throws ExcecaoDeDominio {
        this.produto = ProdutoBuilder.umProduto().construir();
        this.quantidade = 1;
    }

    public static ItemDoPedidoBuilder umItem() throws ExcecaoDeDominio {
        return new ItemDoPedidoBuilder();
    }

    public ItemDoPedido construir() throws ExcecaoDeDominio {
        return new ItemDoPedido(this.produto, this.quantidade);
    }

	public ItemDoPedidoBuilder comProduto(Produto produto) {
        this.produto = produto;
		return this;
	}

	public ItemDoPedidoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
		return this;
	}
}