package com.ecommerce.aplicacao.produto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ProdutoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class RemocaoDeProdutoTeste {

    @Test
    public void deveRemoverUmProduto() throws ExcecaoDeDominio, NotFoundException, ExcecaoDeAplicacao {
        ProdutoRepositorio repositorio = mock(ProdutoRepositorio.class);
        ConsultorDeProduto consultaDeProduto = mock(ConsultorDeProduto.class);
        RemocaoDeProduto remocaoDeProduto = new RemocaoDeProduto(repositorio, consultaDeProduto);
        Produto produtoArmazenado = ProdutoBuilder.umProduto().construir();
        doNothing().when(repositorio).delete(any(Produto.class));
        when(consultaDeProduto.obterObjetoDeDominio(anyLong())).thenReturn(produtoArmazenado);

        remocaoDeProduto.remover(produtoArmazenado.getId());

        verify(repositorio, times(1)).delete(any(Produto.class));
    }
}