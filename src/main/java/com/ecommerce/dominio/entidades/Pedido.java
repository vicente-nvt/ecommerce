package com.ecommerce.dominio.entidades;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.Validacao;

public class Pedido {

    private Cliente cliente;
    private Date data;
    private StatusDoPedido status;
    private Collection<ItemDoPedido> itens;

    public Pedido(Cliente cliente) throws ExcecaoDeDominio {

        Validacao.validar().quando(cliente == null, "O cliente não foi informado").disparar();

        this.cliente = cliente;
        this.data = Calendar.getInstance().getTime();
        this.status = StatusDoPedido.ABERTO;
        this.itens = new HashSet<ItemDoPedido>();
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Date getData() {
        return this.data;
    }

    public void fechar() {
        this.status = StatusDoPedido.FECHADO;
    }

    private boolean verificarStatus(StatusDoPedido statusDoPedido) {
        return this.status == statusDoPedido;
    }

    public boolean estaAberto() {
        return verificarStatus(StatusDoPedido.ABERTO);
    }

    public boolean estaFechado() {
        return verificarStatus(StatusDoPedido.FECHADO);
    }

	public void adicionarItem(ItemDoPedido item) throws ExcecaoDeDominio {

        Validacao.validar()
            .quando(item == null, "Item inválido")
            .disparar();

        this.itens.add(item);
	}

	public Collection<ItemDoPedido> getItens() {
		return Collections.unmodifiableCollection(this.itens);
	}
}