package com.ecommerce.aplicacao.pedido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.ClienteParaConsultaDePedidoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultaDeItemDoPedidoDto;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.StatusDoPedido;

public class ConsultaDePedidoDto implements ObjetoDto<Pedido> {

    private long id;
    private ClienteParaConsultaDePedidoDto cliente;
    private Date data;
    private StatusDoPedido status;
    private Collection<ConsultaDeItemDoPedidoDto> itens;

    public ConsultaDePedidoDto(long id, ClienteParaConsultaDePedidoDto cliente, Date data, StatusDoPedido status,
            Collection<ConsultaDeItemDoPedidoDto> itens) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.itens = itens;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public Date getData() {
        return this.data;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public ClienteParaConsultaDePedidoDto getCliente() {
        return this.cliente;
    }

    public Collection<ConsultaDeItemDoPedidoDto> getItens() {
        return this.itens;
    }
}