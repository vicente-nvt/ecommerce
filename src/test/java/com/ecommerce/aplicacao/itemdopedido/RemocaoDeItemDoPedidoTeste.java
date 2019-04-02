package com.ecommerce.aplicacao.itemdopedido;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class RemocaoDeItemDoPedidoTeste {

    @Test
    public void deveRemoverOItemDoPedido() throws NotFoundException, ExcecaoDeDominio, ExcecaoDeAplicacao {
        ItemDoPedidoRepositorio repositorio = mock(ItemDoPedidoRepositorio.class);
        ConsultorDeItemDoPedido consultorDeItem = mock(ConsultorDeItemDoPedido.class);
        RemocaoDeItemDoPedido remocaoDeItem = new RemocaoDeItemDoPedido(repositorio, consultorDeItem);
        ItemDoPedido itemArmazenado = ItemDoPedidoBuilder.umItem().construir();
        doNothing().when(repositorio).delete(any(ItemDoPedido.class));
        when(consultorDeItem.obterPor(itemArmazenado.getId())).thenReturn(itemArmazenado);

        remocaoDeItem.remover(itemArmazenado.getId());

        verify(repositorio, times(1)).delete(itemArmazenado);
    }
}