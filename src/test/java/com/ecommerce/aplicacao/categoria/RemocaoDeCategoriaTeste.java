package com.ecommerce.aplicacao.categoria;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class RemocaoDeCategoriaTeste {

    @Test
    public void deveRemoverUmaCategoria() throws ExcecaoDeAplicacao, NotFoundException, ExcecaoDeDominio {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        ConsultorDeCategoria consultaDeCategoria = mock(ConsultorDeCategoria.class);
        RemocaoDeCategoria remocaoDeCategoria = new RemocaoDeCategoria(repositorio, consultaDeCategoria);
        Categoria categoriaArmazenada = CategoriaBuilder.umaCategoria()
            .comId(1)
            .comNome("Categoria A")
            .construir();
        doNothing().when(repositorio).delete(any(Categoria.class));
        when(consultaDeCategoria.obterPor(anyLong())).thenReturn(categoriaArmazenada);

        remocaoDeCategoria.remover(categoriaArmazenada.getId());

        verify(repositorio, times(1)).delete(any(Categoria.class));
    }
}