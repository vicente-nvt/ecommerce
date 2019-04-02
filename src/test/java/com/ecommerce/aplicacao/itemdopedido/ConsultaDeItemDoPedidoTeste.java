package com.ecommerce.aplicacao.itemdopedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ItemDoPedidoRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class ConsultaDeItemDoPedidoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private final long id = 1;
    private final long idDoProduto = 3;
    private final int quantidade = 4;
    private final String nomeDoProduto = "Um Produto";
    private final BigDecimal valor = new BigDecimal(10.99);
    private final BigDecimal subtotal = valor.multiply(new BigDecimal(quantidade));

    private ItemDoPedidoRepositorio repositorio;
    private ConsultaDeItemDoPedido consultaDeItemDoPedido;
    private Produto produto;
    private ItemDoPedido itemDoPedido;
    private Optional<ItemDoPedido> optionalItem;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(ItemDoPedidoRepositorio.class);
        consultaDeItemDoPedido = new ConsultaDeItemDoPedido(repositorio);
        produto = ProdutoBuilder.umProduto()
            .comId(idDoProduto)
            .comNome(nomeDoProduto)
            .comPreco(valor)
            .construir();
        itemDoPedido = ItemDoPedidoBuilder.umItem()
            .comProduto(produto)
            .comQuantidade(quantidade)
            .construir();
        optionalItem = Optional.of(itemDoPedido);
    }

    @Test
    public void deveConsultarUmItemDoPedido() throws ExcecaoDeDominio, ExcecaoDeAplicacao, NotFoundException {
        when(repositorio.findById(id)).thenReturn(optionalItem);
        ConsultaDeItemDoPedidoDto itemDoPedidoEsperado = new ConsultaDeItemDoPedidoDto(id, idDoProduto, nomeDoProduto,
                valor, quantidade, subtotal);

        ConsultaDeItemDoPedidoDto itemDoPedidoEncontrado = (ConsultaDeItemDoPedidoDto) consultaDeItemDoPedido
                .consultarPor(id);

        assertTrue(itemDoPedidoEsperado.equals(itemDoPedidoEncontrado));
    }

    @Test
    public void deveObterUmItemDoPedido() throws NotFoundException {
        when(repositorio.findById(anyLong())).thenReturn(optionalItem);

        ItemDoPedido itemEncontrado = consultaDeItemDoPedido.obterPor(id);

        assertEquals(itemDoPedido, itemEncontrado);
    }

    @Test
    public void naoDeveGerarResultadoQuandoOItemNaoExistir() throws ExcecaoDeAplicacao, NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Item não encontrado");
        when(repositorio.findById(anyLong())).thenReturn(Optional.empty());

        consultaDeItemDoPedido.consultarPor(anyLong());
    }}