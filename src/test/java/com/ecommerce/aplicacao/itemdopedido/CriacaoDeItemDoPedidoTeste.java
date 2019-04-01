package com.ecommerce.aplicacao.itemdopedido;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.pedido.ConsultorDePedido;
import com.ecommerce.aplicacao.produto.ConsultorDeProduto;
import com.ecommerce.aplicacao.produto.ProdutoDto;
import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class CriacaoDeItemDoPedidoTeste {

    private final long id = 8;
    private final long idDoProduto = 3;
    private final long idDoPedido = 99;
    private final int quantidade = 5;

    @Test
    public void deveCriarUmItemDoPedido() throws ExcecaoDeDominio, NotFoundException, ExcecaoDeAplicacao {
        ItemDoPedidoRepositorio repositorio = mock(ItemDoPedidoRepositorio.class);
        ConsultorDePedido consultorDePedido = mock(ConsultorDePedido.class);
        ConsultorDeProduto consultorDeProduto = mock(ConsultorDeProduto.class);
        Produto produto = ProdutoBuilder.umProduto().comId(idDoProduto).construir();
        Pedido pedido = PedidoBuilder.umPedido().comId(idDoPedido).construir();
        when(consultorDePedido.obterObjetoDeDominio(anyLong())).thenReturn(pedido);
        when(consultorDeProduto.obterObjetoDeDominio(anyLong())).thenReturn(produto);
        CriacaoDeItemDoPedido criacaoDeItemDoPedido = new CriacaoDeItemDoPedido(repositorio, consultorDePedido,
                consultorDeProduto);
        ItemDoPedidoDto itemDoPedidoDto = new ItemDoPedidoDto(id, idDoPedido, idDoProduto, quantidade);

        criacaoDeItemDoPedido.criar(itemDoPedidoDto);

        verify(repositorio, times(1)).save(any(ItemDoPedido.class));
    }
}