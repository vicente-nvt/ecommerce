package com.ecommerce.aplicacao.produto;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.dominio.entidades.Produto;

public class CadastroDeProdutoDto implements CadastroDto<Produto> {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private long idDaCategoria;

    public CadastroDeProdutoDto() {
    }

    public CadastroDeProdutoDto(String nome, BigDecimal preco, int quantidade, String foto, long idDaCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.foto = foto;
        this.idDaCategoria = idDaCategoria;
    }

	public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setIdDaCategoria(long idDaCategoria) {
        this.idDaCategoria = idDaCategoria;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getFoto() {
        return foto;
    }

    public long getIdDaCategoria() {
        return idDaCategoria;
    }
}