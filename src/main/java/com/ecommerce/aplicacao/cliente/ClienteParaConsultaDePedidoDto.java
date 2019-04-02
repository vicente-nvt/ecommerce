package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.Cliente;

public class ClienteParaConsultaDePedidoDto implements ObjetoDto<Cliente> {

    private long id;
    private String nome;
    private String email;

    public ClienteParaConsultaDePedidoDto(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }
}