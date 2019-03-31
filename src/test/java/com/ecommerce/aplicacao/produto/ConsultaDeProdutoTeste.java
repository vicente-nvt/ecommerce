package com.ecommerce.aplicacao.produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class ConsultaDeProdutoTeste {

    @Rule 
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private static final long id = 1;
    private static final String nome = "Produto A";
    private static final BigDecimal preco = new BigDecimal(299.80);
    private static final int quantidade = 3;
    private static final String foto = "urlDaFoto";
    private static final String nomeDaCategoria = "Brinquedos";
    private static final long idDaCategoria = 2;

    private ProdutoRepositorio repositorio;
    private Categoria categoria;
    private Produto produto;
    private ConsultaDeProduto consultaDeProduto;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(ProdutoRepositorio.class);
        categoria = CategoriaBuilder.umaCategoria()
            .comId(idDaCategoria)
            .comNome(nomeDaCategoria)
            .construir();
        produto = ProdutoBuilder.umProduto()
            .comNome(nome)
            .comPreco(preco)
            .comQuantidade(quantidade)
            .comFoto(foto)
            .comCategoria(categoria)
            .construir();
        
        consultaDeProduto = new ConsultaDeProduto(repositorio);
    }

    @Test
    public void deveConsultarUmProduto() throws ExcecaoDeDominio, ExcecaoDeAplicacao, NotFoundException {
        when(repositorio.findById(anyLong())).thenReturn(produto);
        CategoriaDto categoriaDto = new CategoriaDto(idDaCategoria, nomeDaCategoria);
        ProdutoDto produtoEsperado = new ProdutoDto(id, nome, preco, quantidade, foto, categoriaDto);
        
        ProdutoDto produtoEncontrado = (ProdutoDto) consultaDeProduto.consultarPor(id);

        assertTrue(produtoEsperado.equals(produtoEncontrado));
    }
 
    @Test
    public void deveObterUmProduto() throws NotFoundException {
        when(repositorio.findById(anyLong())).thenReturn(produto);

        Produto produtoEncontrado = consultaDeProduto.obterObjetoDeDominio(id);

        assertEquals(produto, produtoEncontrado);
    }

    @Test
    public void naoDeveGerarResultadoQuandoOProdutoNaoExistir() throws NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Produto não encontrado");
        when(repositorio.findById(anyLong())).thenReturn(null);

        consultaDeProduto.obterObjetoDeDominio(id);
    }
}