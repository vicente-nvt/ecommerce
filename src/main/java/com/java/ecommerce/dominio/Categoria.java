package com.java.ecommerce.dominio;

public class Categoria extends Entidade {

    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

	public String getNome() {
		return this.nome;
	}
}
