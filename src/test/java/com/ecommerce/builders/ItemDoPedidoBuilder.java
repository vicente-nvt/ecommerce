package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.Produto;

public class ItemDoPedidoBuilder {

    private long id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    public ItemDoPedidoBuilder() throws ExcecaoDeDominio {
        this.pedido = PedidoBuilder.umPedido().construir();
        this.produto = ProdutoBuilder.umProduto().construir();
        this.quantidade = 1;
        this.id = 1;
    }

    public static ItemDoPedidoBuilder umItem() throws ExcecaoDeDominio {
        return new ItemDoPedidoBuilder();
    }

    public ItemDoPedidoStub construir() throws ExcecaoDeDominio {
        return new ItemDoPedidoStub(this.id, this.pedido, this.produto, this.quantidade);
    }

	public ItemDoPedidoBuilder comProduto(Produto produto) {
        this.produto = produto;
		return this;
	}

	public ItemDoPedidoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
		return this;
    }

	public ItemDoPedidoBuilder comPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
	}
}

class ItemDoPedidoStub extends ItemDoPedido {

    public ItemDoPedidoStub(long id, Pedido pedido, Produto produto, int quantidade) throws ExcecaoDeDominio {
        super(pedido, produto, quantidade);
        this.id = id;
    }
}