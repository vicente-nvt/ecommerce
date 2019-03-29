package com.java.ecommerce.dominio;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.java.ecommerce.dominio.excecoes.ExcecaoDeDominio;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeEmailInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeEnderecoInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeNomeInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeSenhaInvalida;

public class Cliente extends Entidade {

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    public Cliente(String nome, String email, String senha, Endereco endereco) throws ExcecaoDeDominio {

        if (nome.trim().isEmpty())
            throw new ExcecaoDeNomeInvalido();
        if (!validarEmail(email))
            throw new ExcecaoDeEmailInvalido();
        if (senha.trim().isEmpty())
            throw new ExcecaoDeSenhaInvalida();
        if (endereco == null)
            throw new ExcecaoDeEnderecoInvalido();

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