package com.ecommerce.dominio.objetosdevalor;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

public class Endereco extends ObjetoDeValor {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    protected Endereco() { }

    public Endereco(String rua, String cidade, String bairro, String cep, String estado) 
        throws ExcecaoDeDominio {

        Validacao.validar()
            .quando(rua.trim().isEmpty(), "Rua inválida")
            .quando(cidade.trim().isEmpty(), "Cidade inválida")
            .quando(bairro.trim().isEmpty(), "Bairro inválido")
            .quando(cep.trim().isEmpty(), "Cep inválido")
            .quando(estado.trim().isEmpty(), "Estado inválido")
            .disparar();

        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
    }

    public String getRua() {
        return this.rua;
    }

    public String getCidade() {
        return this.cidade;
    }
    
    public String getBairro() {
        return this.bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public String getEstado() {
        return this.estado;
    }

    public boolean equals(Endereco outroEndereco) {
        return this.rua.equals(outroEndereco.getRua()) &&
            this.cidade.equals(outroEndereco.getCidade()) &&
            this.bairro.equals(outroEndereco.getBairro()) &&
            this.cep.equals(outroEndereco.getCep()) &&
            this.estado.equals(outroEndereco.getEstado());
    }
}