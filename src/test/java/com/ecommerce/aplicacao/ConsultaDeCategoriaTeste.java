package com.ecommerce.aplicacao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultaDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
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
        Categoria categoria = CategoriaBuilder.umaCategoria()
            .comNome(nomeDaCategoria)
            .comId(10)
            .construir();
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        when(repositorio.findById(anyLong())).thenReturn(categoria);
        ConsultaDeCategoria consultaDeCategoria = new ConsultaDeCategoria(repositorio);

        CategoriaDto categoriaEncontrada = (CategoriaDto) consultaDeCategoria.consultarPor(anyLong());

        assertEquals(categoriaEsperada.getNome(), categoriaEncontrada.getNome());
    }
    
}