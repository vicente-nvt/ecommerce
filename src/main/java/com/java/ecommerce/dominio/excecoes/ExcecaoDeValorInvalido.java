package com.java.ecommerce.dominio.excecoes;

public class ExcecaoDeValorInvalido extends ExcecaoDeDominio {

    public ExcecaoDeValorInvalido(String mensagem) {
        super(mensagem);
    }

    public ExcecaoDeValorInvalido() {
        this("O valor é inválido");
    }
}