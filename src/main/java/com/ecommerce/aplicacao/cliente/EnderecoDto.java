package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.base.ObjetoDeValorDto;
import com.ecommerce.dominio.objetosdevalor.Endereco;

public class EnderecoDto implements ObjetoDeValorDto<Endereco> {

    private String rua;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;

    public EnderecoDto() {
    }

    public EnderecoDto(String rua, String cidade, String bairro, String cep, String estado) {
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEstado(String estado) {
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

    public boolean equals(EnderecoDto outroDto) {
        return this.rua.equals(outroDto.getRua()) && this.bairro.equals(outroDto.getBairro())
                && this.cidade.equals(outroDto.getCidade()) && this.cep.equals(outroDto.getCep())
                && this.estado.equals(outroDto.getEstado());
    }
}