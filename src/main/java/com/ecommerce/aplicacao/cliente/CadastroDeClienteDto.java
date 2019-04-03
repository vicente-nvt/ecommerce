package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.dominio.entidades.Cliente;

public class CadastroDeClienteDto implements CadastroDto<Cliente> {

    private String nome;
    private String email;
    private String senha;
    private CadastroDeEnderecoDto endereco;

    public CadastroDeClienteDto() {
    }

    public CadastroDeClienteDto(String nome, String email, String senha, CadastroDeEnderecoDto endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(CadastroDeEnderecoDto endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public CadastroDeEnderecoDto getEndereco() {
        return endereco;
    }
}