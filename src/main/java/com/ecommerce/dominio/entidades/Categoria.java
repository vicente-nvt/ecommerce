package com.ecommerce.dominio.entidades;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

public class Categoria extends Entidade {

    private String nome;

    public Categoria(String nome) throws ExcecaoDeDominio {

        Validacao.validar().quando(nome.trim().isEmpty(), "Nome inválido").disparar();

        this.nome = nome;
    }

	public String getNome() {
		return this.nome;
	}
}
