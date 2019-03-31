package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;

public class CategoriaBuilder {

    private String nome;
    private long id;

    public CategoriaBuilder() {
        this.nome = "Categoria Genérica";
        this.id = 1;
    }

    public static CategoriaBuilder umaCategoria() {
        return new CategoriaBuilder();
    }

    public CategoriaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CategoriaBuilder comId(long id) {
        this.id = id;
        return this;
    }

    public CategoriaStub construir() throws ExcecaoDeDominio {
        CategoriaStub categoria = new CategoriaStub(this.id, this.nome);
        return categoria;
    }
}

class CategoriaStub extends Categoria {

    public CategoriaStub(long id, String nome) throws ExcecaoDeDominio {
        super(nome);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }
}