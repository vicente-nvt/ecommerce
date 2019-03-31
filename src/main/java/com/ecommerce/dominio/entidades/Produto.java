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

        setNome(nome);
        setPreco(preco);
        this.quantidade = quantidade;
        this.foto = foto;
        this.categoria = categoria;
    }

    private void setNome(String nome) throws ExcecaoDeDominio {
        Validacao.validar().quando(nome.trim().isEmpty(), "Nome inválido").disparar();;
        this.nome = nome;
    }

    private void setPreco(BigDecimal preco) throws ExcecaoDeDominio {
        Validacao.validar().quando(preco == null, "Preço inválido").disparar();
        this.preco = preco;
    }

    private void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private void setFoto(String foto) {
        this.foto = foto;
    }

    private void setCategoria(Categoria categoria) {
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

    public boolean equals(Produto outroProduto) {
        return this.getNome().equals(outroProduto.getNome())
            && this.getPreco().compareTo(outroProduto.getPreco()) == 0
            && this.getQuantidade() == outroProduto.getQuantidade()
            && this.getFoto().equals(outroProduto.getFoto())
            && this.getCategoria().equals(outroProduto.getCategoria());
    }

	public void alterarNome(String nome) throws ExcecaoDeDominio {
        setNome(nome);
	}

	public void ajustarPreco(BigDecimal preco) throws ExcecaoDeDominio {
        setPreco(preco);
	}

	public void definirQuantidade(int quantidade) {
        setQuantidade(quantidade);
	}

	public void mudarFoto(String foto) {
        setFoto(foto);
	}

	public void trocarCategoria(Categoria categoria) {
        setCategoria(categoria);
	}
}