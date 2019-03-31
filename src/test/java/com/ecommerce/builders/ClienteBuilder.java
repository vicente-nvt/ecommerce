package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;

public class ClienteBuilder {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;
    private long id;

    public ClienteBuilder() throws ExcecaoDeDominio {
        this.nome = "Nome Genérico";
        this.email = "email@generico.com";
        this.senha = "umaSenha";
        this.endereco = new Endereco("Rua Genérica", "Cidade Genérica", "Bairro Genérico", "00000-000",
            "Estado Genérico");
        this.id = 10;
    }

    public static ClienteBuilder umCliente() throws ExcecaoDeDominio {
        return new ClienteBuilder();
    }

    public ClienteStub construir() throws ExcecaoDeDominio {
        return new ClienteStub(this.id, this.nome, this.email, this.senha, this.endereco);
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

    public ClienteBuilder comId(long id) {
        this.id = id;
        return this;
    }

    public ClienteBuilder comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
}

class ClienteStub extends Cliente {

    public ClienteStub(long id, String nome, String email, String senha, Endereco endereco) throws ExcecaoDeDominio {
        super(nome, email, senha, endereco);
        setId(id);
    }

    public void setId(long id) {
        this.id = id;
    }
}