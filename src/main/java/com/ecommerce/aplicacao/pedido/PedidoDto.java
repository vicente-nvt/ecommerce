package com.ecommerce.aplicacao.pedido;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.cliente.ClienteDto;
import com.ecommerce.aplicacao.itemdopedido.ItemDoPedidoDto;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.StatusDoPedido;

public class PedidoDto implements ObjetoDto<Pedido> {

    private long id;
    private ClienteDto cliente;
    private Date data;
    private StatusDoPedido status;
    private Collection<ItemDoPedidoDto> itens;

    public PedidoDto(long id, ClienteDto cliente, Date data, StatusDoPedido status) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.itens = new HashSet<ItemDoPedidoDto>();
    }

    public void adicionarItem(ItemDoPedidoDto itemDoPedido) {
        this.itens.add(itemDoPedido);
    }

    public void adicionarItens(Collection<ItemDoPedidoDto> itens) {
        this.itens.addAll(itens);
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