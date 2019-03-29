package com.java.ecommerce.builders;

import java.math.BigDecimal;

import com.java.ecommerce.dominio.Categoria;
import com.java.ecommerce.dominio.Produto;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeDominio;

public class ProdutoBuilder {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private Categoria categoria;

    public ProdutoBuilder() {
        this.nome = "Produto Genérico";
        this.preco = new BigDecimal(1.0);
        this.quantidade = 1;
        this.foto = "www.fotosparaexemplo.com/fotos/1";
        this.categoria = new Categoria("Categoria Genérica");
    }

    public static ProdutoBuilder umProduto() {
        return new ProdutoBuilder();
    }

    public ProdutoBuilder comDescricao(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoBuilder comPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public ProdutoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public Produto construir() throws ExcecaoDeDominio {
        return new Produto(this.nome, this.preco, this.quantidade, this.foto, this.categoria);
    }

	public ProdutoBuilder comFoto(String foto) {
        this.foto = foto;
        return this;
	}

	public ProdutoBuilder comCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
	}
}