package com.ecommerce.builders;

import java.math.BigDecimal;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;

public class ProdutoBuilder {

    private String nome;
    private BigDecimal preco;
    private int quantidade;
    private String foto;
    private Categoria categoria;
    private long id;

    public ProdutoBuilder() throws ExcecaoDeDominio {
        this.nome = "Produto Genérico";
        this.preco = new BigDecimal(1.0);
        this.quantidade = 1;
        this.foto = "www.fotosparaexemplo.com/fotos/1";
        this.categoria = CategoriaBuilder.umaCategoria().construir();
        this.id = 1;
    }

    public static ProdutoBuilder umProduto() throws ExcecaoDeDominio {
        return new ProdutoBuilder();
    }

    public ProdutoStub construir() throws ExcecaoDeDominio {
        return new ProdutoStub(this.id, this.nome, this.preco, this.quantidade, this.foto, this.categoria);
    }

    public ProdutoBuilder comNome(String nome) {
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

    public ProdutoBuilder comFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public ProdutoBuilder comCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdutoBuilder comId(long id) {
        this.id = id;
        return this;
    }
}

class ProdutoStub extends Produto {

    public ProdutoStub(long id, String descricao, BigDecimal preco, int quantidade, String foto, Categoria categoria)
            throws ExcecaoDeDominio {
        super(descricao, preco, quantidade, foto, categoria);
        this.id = id;
    }
}