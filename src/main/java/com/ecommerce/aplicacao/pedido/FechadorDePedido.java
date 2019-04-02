package com.ecommerce.aplicacao.pedido;

import javassist.NotFoundException;

public interface FechadorDePedido {

    void fechar(long idDoPedido) throws NotFoundException;
}