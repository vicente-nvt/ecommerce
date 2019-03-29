package com.java.ecommerce.builders;

import com.java.ecommerce.dominio.Endereco;

public class EnderecoBuilder {

    public static EnderecoBuilder umEndereco() {
        return new EnderecoBuilder();
    }

    public Endereco construir(){
        return new Endereco();
    }
}