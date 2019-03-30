package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.Pedido;

public class PedidoBuilder {

    private Cliente cliente;

    public PedidoBuilder() throws ExcecaoDeDominio {
        this.cliente = ClienteBuilder.umCliente().construir();
    }

    public static PedidoBuilder umPedido() throws ExcecaoDeDominio {
        return new PedidoBuilder();
    }

    public Pedido construir() throws ExcecaoDeDominio {
        return new Pedido(this.cliente);
    }

	public PedidoBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
	}
}