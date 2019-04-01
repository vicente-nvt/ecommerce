package com.ecommerce.aplicacao.pedido;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.ClienteDto;
import com.ecommerce.dominio.entidades.Pedido;

public class CriacaoDePedidoDto implements ObjetoDto<Pedido> {

    private long id;
    private ClienteDto cliente;

    public CriacaoDePedidoDto(ClienteDto cliente) {
        this.cliente = cliente;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public ClienteDto getCliente() {
        return this.cliente;
    }
}