package com.java.ecommerce.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import com.java.ecommerce.builders.ProdutoBuilder;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeDominio;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeNomeInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeValorInvalido;

import org.junit.Test;

public class ProdutoTeste {

    @Test
    public void deveCriarUmProdutoComDescricao() throws ExcecaoDeDominio {
        String descricaoEsperada = "Produto A";

        Produto produto = ProdutoBuilder.umProduto().comDescricao(descricaoEsperada).construir();

        assertEquals(descricaoEsperada, produto.getNome());
    }

    @Test(expected = ExcecaoDeNomeInvalido.class)
    public void naoDeveCriarUmProdutoComNomeVazio() throws ExcecaoDeDominio {
        final String descricaoInvalida = " ";

        ProdutoBuilder.umProduto().comDescricao(descricaoInvalida).construir();
    }

    @Test
    public void deveCriarUmProdutoComPreco() throws ExcecaoDeDominio {
        BigDecimal precoEsperado = new BigDecimal(100.0);

        Produto produto = ProdutoBuilder.umProduto().comPreco(precoEsperado).construir();

        assertTrue(precoEsperado.equals(produto.getPreco()));
    }

    @Test(expected = ExcecaoDeValorInvalido.class)
    public void naoDeveCriarUmProdutoSemPreco() throws ExcecaoDeDominio {
        ProdutoBuilder.umProduto().comPreco(null).construir();
    }

    @Test
    public void deveCriarUmProdutoComQuantidadeDefinida() throws ExcecaoDeDominio {
        int quantidadeEsperada = 100;

        Produto produto = ProdutoBuilder.umProduto().comQuantidade(quantidadeEsperada).construir();

        assertEquals(quantidadeEsperada, produto.getQuantidade(), 0.0001);
    }

    @Test
    public void deveCriarUmProdutoComFoto() throws ExcecaoDeDominio {
        String urlDaFotoEsperada = "www.fotos.com.br/fotos/1";

        Produto produto = ProdutoBuilder.umProduto().comFoto(urlDaFotoEsperada).construir();

        assertTrue(urlDaFotoEsperada.equals(produto.getFoto()));
    }

    @Test
    public void deveCriarUmProdutoComCategoria() throws ExcecaoDeDominio {
        Categoria categoriaEsperada = new Categoria("Categoria A");

        Produto produto = ProdutoBuilder.umProduto().comCategoria(categoriaEsperada).construir();

        assertEquals(categoriaEsperada, produto.getCategoria());
    }
}