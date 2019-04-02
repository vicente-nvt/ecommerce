package com.ecommerce.dominio.entidades;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;
import com.ecommerce.dominio.objetosdevalor.Endereco;

@Entity
public class Cliente extends Entidade {

    private String nome;
    private String email;
    private String senha;
    @Embedded
    private Endereco endereco;

    protected Cliente() {
    }

    public Cliente(String nome, String email, String senha, Endereco endereco) throws ExcecaoDeDominio {

        setNome(nome);
        setSenha(senha);
        setEmail(email);
        setEndereco(endereco);
    }

    private void setSenha(String senha) throws ExcecaoDeDominio {
        Validacao.validar().quando(senha.trim().isEmpty(), "Senha inválida").disparar();

        this.senha = senha;
    }

    private void setNome(String nome) throws ExcecaoDeDominio {
        Validacao.validar().quando(nome.trim().isEmpty(), "Nome inválido").disparar();

        this.nome = nome;
    }

    private void setEmail(String email) throws ExcecaoDeDominio {
        Validacao.validar().quando(!validarEmail(email), "E-mail inválido").disparar();

        this.email = email;
    }

    private void setEndereco(Endereco endereco) throws ExcecaoDeDominio {
        Validacao.validar().quando(endereco == null, "Endereço inválido").disparar();

        this.endereco = endereco;
    }

    private boolean validarEmail(String email) {
        return email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void alterarNome(String nome) throws ExcecaoDeDominio {
        setNome(nome);
    }

    public void trocarEmail(String email) throws ExcecaoDeDominio {
        setEmail(email);
    }

    public void trocarSenha(String senha) throws ExcecaoDeDominio {
        setSenha(senha);
    }

    public void mudarEndereco(Endereco endereco) throws ExcecaoDeDominio {
        setEndereco(endereco);
    }

    public boolean equals(Cliente outroCliente) {
        return this.id.equals(outroCliente.getId()) && this.nome.equals(outroCliente.getNome())
                && this.email.equals(outroCliente.getEmail()) && this.senha.equals(outroCliente.getSenha())
                && this.endereco.equals(outroCliente.getEndereco());
    }

}