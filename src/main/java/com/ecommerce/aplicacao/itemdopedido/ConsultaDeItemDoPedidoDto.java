package com.ecommerce.aplicacao.itemdopedido;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.ItemDoPedido;

public class ConsultaDeItemDoPedidoDto implements ObjetoDto<ItemDoPedido> {

    private long id;
    private long idDoProduto;
    private String nomeDoProduto;
    private BigDecimal valor;
    private int quantidade;
    private BigDecimal subtotal;

    public ConsultaDeItemDoPedidoDto(long id, long idDoProduto, String nomeDoProduto, BigDecimal valor, int quantidade,
            BigDecimal subtotal) {
        this.id = id;
        this.idDoProduto = idDoProduto;
        this.nomeDoProduto = nomeDoProduto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public long getIdDoProduto() {
        return this.idDoProduto;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public boolean equals(ConsultaDeItemDoPedidoDto outroDto) {
        return this.getIdDoProduto() == outroDto.getIdDoProduto()
            && this.getNomeDoProduto().equals(outroDto.getNomeDoProduto())
            && this.getQuantidade() == outroDto.getQuantidade()
            && this.getValor().compareTo(outroDto.getValor()) == 0
            && this.getSubtotal().compareTo(outroDto.getSubtotal()) == 0;
    }
}