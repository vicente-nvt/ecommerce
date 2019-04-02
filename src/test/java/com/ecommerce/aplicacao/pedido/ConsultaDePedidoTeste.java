package com.ecommerce.aplicacao.pedido;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.cliente.ClienteParaConsultaDePedidoDto;
import com.ecommerce.aplicacao.itemdopedido.ConsultaDeItemDoPedidoDto;
import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.StatusDoPedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.junit.Ignore;
import org.junit.Test;

import javassist.NotFoundException;

public class ConsultaDePedidoTeste {

    private final long idDoPedido = 10;
    private final long idDoCliente = 5;
    private final String nome = "Cliente A";
    private final String email = "cliente@email.com";
    private final Date data = Calendar.getInstance().getTime();

    @Ignore
    @Test
    public void deveConsultarUmPedido() throws ExcecaoDeAplicacao, NotFoundException, ExcecaoDeDominio {
        PedidoRepositorio repositorio = mock(PedidoRepositorio.class);
        Pedido pedido = PedidoBuilder.umPedido().construir();
        ItemDoPedido item = ItemDoPedidoBuilder.umItem().construir();
        pedido.adicionarItem(item);
        when(repositorio.findById(anyLong())).thenReturn(Optional.of(pedido));
        ClienteParaConsultaDePedidoDto cliente = new ClienteParaConsultaDePedidoDto(idDoCliente, nome, email);
        ConsultaDePedidoDto pedidoEsperado = new ConsultaDePedidoDto(idDoPedido, cliente, data, StatusDoPedido.ABERTO,
                new ArrayList<ConsultaDeItemDoPedidoDto>());
        ConsultaDePedido consultaDePedido = new ConsultaDePedido(repositorio);

        ConsultaDePedidoDto pedidoEncontrado = (ConsultaDePedidoDto) consultaDePedido.consultarPor(idDoPedido);

        assertEquals(pedidoEsperado.getCliente().getNome(), pedidoEncontrado.getCliente().getNome());
        assertEquals(pedidoEsperado.getData(), pedidoEncontrado.getData());
    }
}