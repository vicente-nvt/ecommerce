package com.ecommerce.aplicacao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.dtos.CategoriaDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class ConsultaDeCategoriaTeste {

    @Test
    public void deveConsultarUmaCategoria() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        final String nomeDaCategoria = "Categoria A";
        CategoriaDto categoriaEsperada = new CategoriaDto(nomeDaCategoria);
        Categoria categoria = new Categoria(nomeDaCategoria);
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        when(repositorio.findById(anyInt())).thenReturn(categoria);
        ConsultaDeCategoria consultaDeCategoria = new ConsultaDeCategoria(repositorio);

        CategoriaDto categoriaEncontrada = (CategoriaDto) consultaDeCategoria.consultarPor(anyInt());

        assertEquals(categoriaEsperada.getNome(), categoriaEncontrada.getNome());
    }
    
}