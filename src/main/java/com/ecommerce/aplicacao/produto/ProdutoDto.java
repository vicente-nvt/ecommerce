package com.ecommerce.aplicacao.produto;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.dominio.entidades.Produto;

public class ProdutoDto implements ObjetoDto<Produto> {

    private long id;
    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private CategoriaDto categoria;

    public ProdutoDto(long id, String nome, BigDecimal preco, int quantidade, String foto, CategoriaDto categoria){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.foto = foto;
        this.categoria = categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco ) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setCategoria(CategoriaDto categoria) {
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

    public CategoriaDto getCategoria() {
       return this.categoria;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }
}