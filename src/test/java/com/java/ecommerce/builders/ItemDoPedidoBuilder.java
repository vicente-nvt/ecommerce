package com.java.ecommerce.builders;

import com.java.ecommerce.dominio.ExcecaoDeDominio;
import com.java.ecommerce.dominio.ItemDoPedido;
import com.java.ecommerce.dominio.Produto;

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