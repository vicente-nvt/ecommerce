package com.ecommerce.dominio.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

@Entity
public class ItemDoPedido extends Entidade {

    @OneToOne
    private Produto produto;
    private int quantidade;
    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    private Pedido pedido;

    protected ItemDoPedido() {
    }

    public ItemDoPedido(Pedido pedido, Produto produto, int quantidade) throws ExcecaoDeDominio {

        Validacao.validar().quando(produto == null, "O Produto não foi informado")
                .quando(pedido == null, "O Pedido não foi informado").quando(quantidade <= 0, "A quantidade é inválida")
                .disparar();

        this.pedido = pedido;
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