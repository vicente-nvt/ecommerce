package com.ecommerce.dominio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PedidoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    @Test
    public void deveCriarUmPedidoComCliente() throws ExcecaoDeDominio {
        Cliente clienteEsperado = ClienteBuilder.umCliente().construir();

        Pedido pedido = PedidoBuilder.umPedido().comCliente(clienteEsperado).construir();

        assertEquals(clienteEsperado, pedido.getCliente());
    }

    @Test
    public void naoDeveCriarUmPedidoSemCliente() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("O cliente não foi informado");
        Cliente clienteNaoInformado = null;

        PedidoBuilder.umPedido().comCliente(clienteNaoInformado).construir();
    }

    @Test
    public void deveCriarUmPedidoComData() throws ExcecaoDeDominio {
        String padrao = "dd/MM/yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(padrao);
        Date agora = Calendar.getInstance().getTime();
        String dataEsperada = dateFormat.format(agora);

        Pedido pedido = PedidoBuilder.umPedido().construir();

        assertEquals(dataEsperada, dateFormat.format(pedido.getData()));
    }

    @Test
    public void deveCriarUmPedidoComStatusAberto() throws ExcecaoDeDominio {
        Pedido pedido = PedidoBuilder.umPedido().construir();
        assertTrue(pedido.estaAberto());

    }

    @Test
    public void deveFecharOPedido() throws ExcecaoDeDominio {
        Pedido pedido = PedidoBuilder.umPedido().construir();

        pedido.fechar();

        assertTrue(pedido.estaFechado());
    }

    @Test
    public void deveCriarOPedidoComListaDeItensVazia() throws ExcecaoDeDominio {
        Pedido pedido = PedidoBuilder.umPedido().construir();

        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    public void deveAdicionarUmItemAoPedido() throws ExcecaoDeDominio {
        ItemDoPedido item = ItemDoPedidoBuilder.umItem().construir();
        ItemDoPedido[] itensDoPedido = { item };
        Pedido pedido = PedidoBuilder.umPedido().construir();

        pedido.adicionarItem(item);

        assertArrayEquals(itensDoPedido, pedido.getItens().toArray());
    }

    @Test
    public void naoDeveAdicionarUmItemInvalidoAoPedido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Item inválido");
        Pedido pedido = PedidoBuilder.umPedido().construir();

        pedido.adicionarItem(null);
    }
}