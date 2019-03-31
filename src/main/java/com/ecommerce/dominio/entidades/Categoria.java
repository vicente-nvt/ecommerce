package com.ecommerce.dominio.entidades;

import javax.persistence.Entity;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

@Entity
public class Categoria extends Entidade {

    private String nome;

    protected Categoria() { }

    public Categoria(String nome) throws ExcecaoDeDominio {
        validarNome(nome);

        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void atualizarNome(String nome) throws ExcecaoDeDominio {
        validarNome(nome);

        this.nome = nome;
    }

    private void validarNome(String nome) throws ExcecaoDeDominio {
        Validacao.validar().quando(nome.trim().isEmpty(), "Nome inválido").disparar();
    }
}
