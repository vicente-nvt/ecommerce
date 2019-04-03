package com.ecommerce.aplicacao.produto;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.categoria.ConsultaDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.junit.Test;
import org.mockito.InOrder;

import javassist.NotFoundException;

public class EdicaoDeProdutoTeste {

    private static final int idDaCategoria = 9;
    private static final String nomeDaCategoria = "Nova categoria";
    private static final long id = 8;
    private static final String nome = "Novo nome";
    private static final BigDecimal preco = new BigDecimal(1990.80);
    private static final int quantidade = 300;
    private static final String foto = "www.fotos.com.br/fotosLegais/88";

    @Test
    public void deveAlterarUmProduto() throws NotFoundException, ExcecaoDeAplicacao, ExcecaoDeDominio {
        ProdutoRepositorio repositorio = mock(ProdutoRepositorio.class);
        ConsultaDeProduto consultaDeProduto = mock(ConsultaDeProduto.class);
        ConsultaDeCategoria consultaDeCategoria = mock(ConsultaDeCategoria.class);
        EdicaoDeProduto edicaoDeProduto = new EdicaoDeProduto(repositorio, consultaDeProduto, consultaDeCategoria);
        Categoria categoria = CategoriaBuilder.umaCategoria()
            .comId(idDaCategoria)
            .comNome(nomeDaCategoria)
            .construir();
        Produto produtoArmazenado = ProdutoBuilder.umProduto().comId(id).construir();
        Produto produtoAlterado = ProdutoBuilder.umProduto()
            .comId(id)
            .comNome(nome)
            .comPreco(preco)
            .comQuantidade(quantidade)
            .comFoto(foto)
            .comCategoria(categoria)
            .construir();
        CadastroDeProdutoDto produtoDto = new CadastroDeProdutoDto(nome, preco, quantidade, foto, idDaCategoria);
        when(consultaDeCategoria.obterPor(idDaCategoria)).thenReturn(categoria);
        when(consultaDeProduto.obterPor(id)).thenReturn(produtoArmazenado);
        InOrder emOrdem = inOrder(consultaDeProduto, repositorio);

        edicaoDeProduto.editar(id, produtoDto);

        assertTrue(produtoAlterado.equals(produtoArmazenado));
        emOrdem.verify(consultaDeProduto, times(1)).obterPor(anyLong());
        emOrdem.verify(repositorio, times(1)).save(any(Produto.class));
    }
}