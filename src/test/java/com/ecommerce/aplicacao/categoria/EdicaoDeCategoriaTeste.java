package com.ecommerce.aplicacao.categoria;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Test;
import org.mockito.InOrder;

import javassist.NotFoundException;

public class EdicaoDeCategoriaTeste {

    private final long id = 8;

    @Test
    public void deveEditarUmaCategoria() throws ExcecaoDeAplicacao, NotFoundException, ExcecaoDeDominio {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        ConsultorDeCategoria consultaDeCategoria = mock(ConsultorDeCategoria.class);
        InOrder emOrdem = inOrder(repositorio, consultaDeCategoria);
        Categoria categoriaArmazenada = CategoriaBuilder.umaCategoria().comId(id).construir();
        when(repositorio.save(any(Categoria.class))).thenReturn(categoriaArmazenada);
        when(consultaDeCategoria.obterPor(anyLong())).thenReturn(categoriaArmazenada);
        final String novoNome = "Categoria B";
        CadastroDeCategoriaDto categoriaDto = new CadastroDeCategoriaDto(novoNome);
        EdicaoDeCategoria edicaoDeCategoria = new EdicaoDeCategoria(repositorio, consultaDeCategoria);

        edicaoDeCategoria.editar(id, categoriaDto);

        assertEquals(novoNome, categoriaArmazenada.getNome());
        emOrdem.verify(consultaDeCategoria, times(1)).obterPor(anyLong());
        emOrdem.verify(repositorio, times(1)).save(any(Categoria.class));
    }
}