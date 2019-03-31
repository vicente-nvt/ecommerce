package com.ecommerce.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProdutoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    @Test
    public void deveCriarUmProdutoComDescricao() throws ExcecaoDeDominio {
        String descricaoEsperada = "Produto A";

        Produto produto = ProdutoBuilder.umProduto().comNome(descricaoEsperada).construir();

        assertEquals(descricaoEsperada, produto.getNome());
    }

    @Test
    public void naoDeveCriarUmProdutoComNomeVazio() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        final String descricaoInvalida = " ";

        ProdutoBuilder.umProduto().comNome(descricaoInvalida).construir();
    }

    @Test
    public void deveCriarUmProdutoComPreco() throws ExcecaoDeDominio {
        BigDecimal precoEsperado = new BigDecimal(100.0);

        Produto produto = ProdutoBuilder.umProduto().comPreco(precoEsperado).construir();

        assertTrue(precoEsperado.equals(produto.getPreco()));
    }

    @Test
    public void naoDeveCriarUmProdutoSemPreco() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Preço inválido");

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

    @Test
    public void deveAlterarNome() throws ExcecaoDeDominio {
        String novoNome = "Um Novo Nome";
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.alterarNome(novoNome);

        assertEquals(novoNome, produto.getNome());
    }

    @Test
    public void naoDeveAlterarParaUmNomeInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.alterarNome(" ");
    }

    @Test
    public void deveAjustarOPreco() throws ExcecaoDeDominio {
        BigDecimal novoPreco = new BigDecimal(1.99);
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.ajustarPreco(novoPreco);

        assertEquals(0, novoPreco.compareTo(produto.getPreco()));
    }

    @Test
    public void naoDeveAjustarParaUmPrecoInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Preço inválido");
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.ajustarPreco(null);
    }

    @Test
    public void deveDefinirAQuantidade() throws ExcecaoDeDominio {
        int novaQuantidade = 199;
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.definirQuantidade(novaQuantidade);

        assertEquals(novaQuantidade, produto.getQuantidade());
    }

    @Test
    public void deveMudarAFoto() throws ExcecaoDeDominio {
        String novaFoto = "novaUrl";
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.mudarFoto(novaFoto);

        assertEquals(novaFoto, produto.getFoto());
    }

    @Test
    public void deveAlterarOPreco() throws ExcecaoDeDominio {
        Categoria novaCategoria = CategoriaBuilder.umaCategoria()
            .comId(108)
            .comNome("Ferramentas")
            .construir();
        Produto produto = ProdutoBuilder.umProduto().construir();

        produto.trocarCategoria(novaCategoria);

        assertEquals(novaCategoria, produto.getCategoria());
    }
}