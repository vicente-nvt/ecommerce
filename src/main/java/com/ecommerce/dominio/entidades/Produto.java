package com.ecommerce.dominio.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

@Entity
public class Produto extends Entidade {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    @ManyToOne
    private Categoria categoria;

    protected Produto() { }

    public Produto(String nome, BigDecimal preco, int quantidade, String foto, Categoria categoria)
            throws ExcecaoDeDominio {

        Validacao.validar()
            .quando(nome.trim().isEmpty(), "Nome inválido")
            .quando(preco == null, "Preço inválido")
            .disparar();

        this.nome = nome;
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