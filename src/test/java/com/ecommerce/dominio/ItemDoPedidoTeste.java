package com.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import com.ecommerce.builders.ItemDoPedidoBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.entidades.ItemDoPedido;
import com.ecommerce.dominio.entidades.Produto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ItemDoPedidoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    @Test
    public void deveCriarUmItemComProdutoInformado() throws ExcecaoDeDominio {
        Produto produtoEsperado = ProdutoBuilder.umProduto().construir();

        ItemDoPedido itemDoPedido = ItemDoPedidoBuilder.umItem().comProduto(produtoEsperado).construir();

        assertEquals(produtoEsperado, itemDoPedido.getProduto());
    }

    @Test
    public void naoDeveCriarUmItemSemProdutoInformado() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("O Produto não foi informado");
        Produto produtoNaoInformado = null;

        ItemDoPedidoBuilder.umItem().comProduto(produtoNaoInformado).construir();
    }

    @Test
    public void deveCriarUmItemComQuantidadeInformada() throws ExcecaoDeDominio {
        int quantidadeEsperada = 100;

        ItemDoPedido itemDoPedido = ItemDoPedidoBuilder.umItem().comQuantidade(quantidadeEsperada).construir();

        assertEquals(quantidadeEsperada, itemDoPedido.getQuantidade());
    }

    @Test
    public void naoDeveCriarUmItemComQuantidadeInvalida() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("A quantidade é inválida");
        int quantidadeInvalida = 0;

        ItemDoPedidoBuilder.umItem().comQuantidade(quantidadeInvalida).construir();
    }

    @Test
    public void deveAtribuirOValorDoItemAoCriarComProduto() throws ExcecaoDeDominio {
        BigDecimal valorEsperado = new BigDecimal(199.99);
        Produto produto = ProdutoBuilder.umProduto().comPreco(valorEsperado).construir();

        ItemDoPedido itemDoPedido = ItemDoPedidoBuilder.umItem().comProduto(produto).construir();

        assertEquals(valorEsperado, itemDoPedido.getValor());
    }

    @Test
    public void deveCalcularOSubtotalDoItem() throws ExcecaoDeDominio {
        int quantidade = 13;
        BigDecimal valorDoProduto = new BigDecimal(199.99);
        BigDecimal subtotalEsperado = valorDoProduto.multiply(new BigDecimal(quantidade));
        Produto produto = ProdutoBuilder.umProduto().comPreco(valorDoProduto).construir();

        ItemDoPedido itemDoPedido = ItemDoPedidoBuilder.umItem()
            .comProduto(produto)
            .comQuantidade(quantidade)
            .construir();

        assertEquals(subtotalEsperado, itemDoPedido.getSubtotal());
    }

}