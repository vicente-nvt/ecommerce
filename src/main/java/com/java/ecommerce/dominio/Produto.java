package com.java.ecommerce.dominio;

import java.math.BigDecimal;

import com.java.ecommerce.dominio.excecoes.*;

public class Produto extends Entidade {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private Categoria categoria;

    public Produto(String descricao, BigDecimal preco, int quantidade, String foto, Categoria categoria)
            throws ExcecaoDeDominio {

        if (descricao.trim().isEmpty())
            throw new ExcecaoDeNomeInvalido();
        if (preco == null)
            throw new ExcecaoDeValorInvalido("Preço inválido");

        this.nome = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.foto = foto;
        this.categoria = categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getFoto() {
        return this.foto;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }
}