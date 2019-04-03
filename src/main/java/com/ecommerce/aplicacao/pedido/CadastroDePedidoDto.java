package com.ecommerce.aplicacao.pedido;

import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.dominio.entidades.Pedido;

public class CadastroDePedidoDto implements CadastroDto<Pedido> {

    private long idDoCliente;

    public CadastroDePedidoDto() {
    }

    public CadastroDePedidoDto(long idDoCliente) {
        this.idDoCliente = idDoCliente;
    }

    public void setIdDoCliente(long idDoCliente) {
        this.idDoCliente = idDoCliente;
    }

    public long getIdDoCliente() {
        return idDoCliente;
    }
}