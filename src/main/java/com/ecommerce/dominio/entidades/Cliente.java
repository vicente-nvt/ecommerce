package com.ecommerce.dominio.entidades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

public class Cliente extends Entidade {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    public Cliente(String nome, String email, String senha, Endereco endereco) throws ExcecaoDeDominio {


        Validacao.validar()
            .quando(nome.trim().isEmpty(), "Nome inválido")
            .quando(!validarEmail(email), "E-mail inválido")
            .quando(senha.trim().isEmpty(), "Senha inválida")
            .quando(endereco == null, "Endereço inválido")
            .disparar();

        this.nome = nome;
        this.email = email;
        this.endereco = endereco;

        try {
            this.senha = criptografarSenha(senha);
        } catch (Exception e) {
            throw new ExcecaoDeDominio(e.getMessage());
        }
    }

    private String criptografarSenha(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(senha.getBytes("ASCII"));
        byte[] hash = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
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
}