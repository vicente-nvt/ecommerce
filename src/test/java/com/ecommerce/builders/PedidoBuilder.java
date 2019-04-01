package com.ecommerce.builders;

import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.Pedido;

public class PedidoBuilder {

    private Cliente cliente;
    private long id;

    public PedidoBuilder() throws ExcecaoDeDominio {
        this.cliente = ClienteBuilder.umCliente().construir();
        this.id = 78;
    }

    public static PedidoBuilder umPedido() throws ExcecaoDeDominio {
        return new PedidoBuilder();
    }

    public PedidoStub construir() throws ExcecaoDeDominio {
        return new PedidoStub(this.id, this.cliente);
    }

	public PedidoBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }
    
    public PedidoBuilder comId(long id) {
        this.id = id;
        return this;
    }
}

class PedidoStub extends Pedido {

    public PedidoStub(long id, Cliente cliente) throws ExcecaoDeDominio {
        super(cliente);
        this.id = id;
    }
}