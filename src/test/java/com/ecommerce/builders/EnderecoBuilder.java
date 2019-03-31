package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.objetosdevalor.Endereco;

public class EnderecoBuilder {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    public EnderecoBuilder() {
        this.rua = "Rua Genérica";
        this.cidade = "Cidade Genérica";
        this.bairro = "Bairro Genérico";
        this.cep = "11222-333";
        this.estado = "Estado Genérico";
    }

    public static EnderecoBuilder umEndereco() {
        return new EnderecoBuilder();
    }

    public Endereco construir() throws ExcecaoDeDominio {
        return new Endereco(this.rua, this.cidade, this.bairro, this.cep, this.estado);
    }

	public EnderecoBuilder comRua(String rua) {
        this.rua = rua;
		return this;
	}

	public EnderecoBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
	}

	public EnderecoBuilder comBairro(String bairro) {
        this.bairro = bairro;
        return this;
	}

	public EnderecoBuilder comCep(String cep) {
        this.cep = cep;
		return this;
	}

	public EnderecoBuilder comEstado(String estado) {
        this.estado = estado;
		return this;
	}
}