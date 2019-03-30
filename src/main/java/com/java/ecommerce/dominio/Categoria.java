package com.java.ecommerce.dominio;

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
