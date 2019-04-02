package com.ecommerce.aplicacao.pedido;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class FechamentoDePedidoTeste {

    @Test
    public void deveFecharOPedido() throws ExcecaoDeDominio, NotFoundException {
        PedidoRepositorio repositorio = mock(PedidoRepositorio.class);
        ConsultorDePedido consultorDePedido = mock(ConsultorDePedido.class);
        Pedido pedido = PedidoBuilder.umPedido().construir();
        when(consultorDePedido.obterPor(pedido.getId())).thenReturn(pedido);
        FechamentoDePedido fechamentoDePedido = new FechamentoDePedido(repositorio, consultorDePedido);

        fechamentoDePedido.fechar(pedido.getId());

        assertTrue(pedido.estaFechado());
        verify(repositorio, times(1)).save(pedido);
    }
    
}