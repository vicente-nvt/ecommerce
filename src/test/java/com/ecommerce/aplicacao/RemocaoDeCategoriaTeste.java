package com.ecommerce.aplicacao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.categoria.RemocaoDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.dominio.CategoriaTeste;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class RemocaoDeCategoriaTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private CategoriaRepositorio repositorio;
    private Categoria categoriaArmazenada;
    private RemocaoDeCategoria remocaoDeCategoria;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(CategoriaRepositorio.class);
        remocaoDeCategoria = new RemocaoDeCategoria(repositorio);
        categoriaArmazenada = CategoriaBuilder.umaCategoria()
            .comId(1)
            .comNome("Categoria A")
            .construir();
    }

    @Test
    public void deveRemoverUmaCategoria() throws ExcecaoDeAplicacao, NotFoundException {
        doNothing().when(repositorio).delete(any(Categoria.class));
        when(repositorio.findById(anyLong())).thenReturn(categoriaArmazenada);

        remocaoDeCategoria.remover(categoriaArmazenada.getId());

        verify(repositorio, times(1)).delete(any(Categoria.class));
    }

    @Test
    public void deveDispararExcecaoAoTentarRemoverUmaCategoriaInexistente() throws ExcecaoDeAplicacao, NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Categoria inexistente");
        doNothing().when(repositorio).delete(any(Categoria.class));

        remocaoDeCategoria.remover(categoriaArmazenada.getId());
    }
}