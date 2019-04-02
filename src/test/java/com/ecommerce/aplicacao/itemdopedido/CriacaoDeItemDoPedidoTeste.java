package com.ecommerce.aplicacao.itemdopedido;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.pedido.ConsultorDePedido;
import com.ecommerce.aplicacao.produto.ConsultorDeProduto;
import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class CriacaoDeItemDoPedidoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private final long id = 8;
    private final long idDoProduto = 3;
    private final long idDoPedido = 99;
    private final int quantidade = 5;

    private ItemDoPedidoRepositorio repositorio;
    private ConsultorDePedido consultorDePedido;
    private ConsultorDeProduto consultorDeProduto;
    private Produto produto;
    private Pedido pedido;
    private ItemDoPedido itemDoPedido;
    private CriacaoDeItemDoPedido criacaoDeItemDoPedido;
    private CriacaoDeItemDoPeditoDto itemDoPedidoDto;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(ItemDoPedidoRepositorio.class);
        consultorDePedido = mock(ConsultorDePedido.class);
        consultorDeProduto = mock(ConsultorDeProduto.class);
        produto = ProdutoBuilder.umProduto().comId(idDoProduto).construir();
        pedido = PedidoBuilder.umPedido().comId(idDoPedido).construir();
        itemDoPedido = ItemDoPedidoBuilder.umItem().construir();
        criacaoDeItemDoPedido = new CriacaoDeItemDoPedido(repositorio, consultorDePedido, consultorDeProduto);
        itemDoPedidoDto = new CriacaoDeItemDoPeditoDto(id, idDoPedido, idDoProduto, quantidade);
    }

    @Test
    public void deveCriarUmItemDoPedido() throws ExcecaoDeDominio, NotFoundException, ExcecaoDeAplicacao {
        when(consultorDePedido.obterPor(anyLong())).thenReturn(pedido);
        when(consultorDeProduto.obterPor(anyLong())).thenReturn(produto);
        when(repositorio.save(any(ItemDoPedido.class))).thenReturn(itemDoPedido);

        criacaoDeItemDoPedido.criar(itemDoPedidoDto);

        verify(repositorio, times(1)).save(any(ItemDoPedido.class));
    }
}