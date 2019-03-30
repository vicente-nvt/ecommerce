package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.Endereco;

public class ClienteBuilder {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    public ClienteBuilder() throws ExcecaoDeDominio {
        this.nome = "Nome Genérico";
        this.email = "email@generico.com";
        this.senha = "umaSenha";
        this.endereco = new Endereco("Rua Genérica", "Cidade Genérica", "Bairro Genérico", "00000-000",
                "Estado Genérico");
    }

    public static ClienteBuilder umCliente() throws ExcecaoDeDominio {
        return new ClienteBuilder();
    }

    public Cliente construir() throws ExcecaoDeDominio {
        return new Cliente(this.nome, this.email, this.senha, this.endereco);
    }

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public ClienteBuilder comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
}