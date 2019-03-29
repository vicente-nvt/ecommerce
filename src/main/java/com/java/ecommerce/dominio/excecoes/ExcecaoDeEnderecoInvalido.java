package com.java.ecommerce.dominio.excecoes;

public class ExcecaoDeEnderecoInvalido extends ExcecaoDeDominio {

    public ExcecaoDeEnderecoInvalido(String mensagem) {
        super(mensagem);
    }

    public ExcecaoDeEnderecoInvalido(){
        this("Endereço inválido");
    }
}