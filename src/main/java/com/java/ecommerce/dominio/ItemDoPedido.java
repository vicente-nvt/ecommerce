package com.java.ecommerce.dominio;

import java.math.BigDecimal;

public class ItemDoPedido {

    private Produto produto;
    private int quantidade;
    private BigDecimal valor;
    
    public ItemDoPedido(Produto produto, int quantidade) throws ExcecaoDeDominio {

        Validacao.validar()
            .quando(produto == null, "O Produto não foi informado")
            .quando(quantidade <= 0, "A quantidade é inválida")
            .disparar();;

        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = produto.getPreco();
    }

    public Produto getProduto() {
		return this.produto;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public BigDecimal getSubtotal() {
        BigDecimal subtotal = this.valor.multiply(new BigDecimal(this.quantidade));
		return subtotal;
	}
}