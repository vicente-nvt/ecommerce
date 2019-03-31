package com.ecommerce.aplicacao.categoria;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.EdicaoDeCategoria;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;

import javassist.NotFoundException;

public class EdicaoDeCategoriaTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private CategoriaRepositorio repositorio;
    private ConsultorDeCategoria consultaDeCategoria;
    private Categoria categoriaArmazenada;
    private EdicaoDeCategoria edicaoDeCategoria;
    private final String novoNome = "Categoria B";
    private CategoriaDto categoriaDto;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(CategoriaRepositorio.class);
        consultaDeCategoria = mock(ConsultorDeCategoria.class);
        categoriaArmazenada = new Categoria("Categoria A");
        edicaoDeCategoria = new EdicaoDeCategoria(repositorio, consultaDeCategoria);
        categoriaDto = new CategoriaDto();
        categoriaDto.setNome(novoNome);
    }

    @Test
    public void deveEditarUmaCategoria() throws ExcecaoDeAplicacao, NotFoundException {
        when(repositorio.save(any(Categoria.class))).thenReturn(categoriaArmazenada);
        when(consultaDeCategoria.obterObjetoDeDominio(anyLong())).thenReturn(categoriaArmazenada);
        InOrder emOrdem = inOrder(repositorio, consultaDeCategoria);

        edicaoDeCategoria.editar(categoriaDto);

        assertEquals(novoNome, categoriaArmazenada.getNome());
        emOrdem.verify(consultaDeCategoria, times(1)).obterObjetoDeDominio(anyLong());
        emOrdem.verify(repositorio, times(1)).save(any(Categoria.class));
    }
}