package com.ecommerce.dominio.entidades;

import java.math.BigDecimal;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

public class Produto extends Entidade {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private Categoria categoria;

    public Produto(String descricao, BigDecimal preco, int quantidade, String foto, Categoria categoria)
            throws ExcecaoDeDominio {

        Validacao.validar()
            .quando(descricao.trim().isEmpty(), "Nome inválido")
            .quando(preco == null, "Preço inválido")
            .disparar();

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