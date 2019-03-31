package com.ecommerce.aplicacao.produto;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultorDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class CriacaoDeProdutoTeste {

    private final long id = 1;
    private final String nome = "Produto A";
    private final BigDecimal preco = new BigDecimal(1);
    private final int quantidade = 10;
    private final String foto = "www.fotos.com/produtos/1";

    @Test
    public void deveArmazenarUmProduto() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        ProdutoRepositorio repositorio = mock(ProdutoRepositorio.class);
        ConsultorDeCategoria consultorDeCategoria = mock(ConsultorDeCategoria.class);
        Produto produtoCriado = ProdutoBuilder.umProduto().construir();
        when(repositorio.save(any(Produto.class))).thenReturn(produtoCriado);
        Categoria categoria = CategoriaBuilder.umaCategoria().comId(10).comNome("Categoria A").construir();
        when(consultorDeCategoria.obterObjetoDeDominio(anyLong())).thenReturn(categoria);
        CriacaoDeProduto criacaoDeProduto = new CriacaoDeProduto(repositorio, consultorDeCategoria);
        CategoriaDto categoriaDto = new CategoriaDto("Categoria A");
        ProdutoDto produtoDto = new ProdutoDto(id, nome, preco, quantidade, foto, categoriaDto);

        criacaoDeProduto.criar(produtoDto);

        verify(repositorio, times(1)).save(any(Produto.class));
    }
}