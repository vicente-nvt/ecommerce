package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.Categoria;

public class CategoriaDto implements ObjetoDto<Categoria> {

    private long id;
    private String nome;

    public CategoriaDto() { }

    public CategoriaDto(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() { 
        return this.id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome(){ 
        return this.nome;
    }
}