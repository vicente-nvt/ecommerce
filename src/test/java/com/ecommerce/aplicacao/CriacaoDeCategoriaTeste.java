package com.ecommerce.aplicacao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.dtos.CategoriaDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Test;

public class CriacaoDeCategoriaTeste {

    @Test
    public void devePersistirUmaCategoria() throws ExcecaoDeAplicacao, ExcecaoDeDominio {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        Categoria categoriaArmazenada = new Categoria("Categoria A");
        when(repositorio.save(any(Categoria.class))).thenReturn(categoriaArmazenada);
        CriacaoDeCategoria criadorDeCategoria = new CriacaoDeCategoria(repositorio);
        String nomeDaCategoria = "Categoria A";
        CategoriaDto categoriaDto = new CategoriaDto(nomeDaCategoria);
        
        criadorDeCategoria.criar(categoriaDto);

        verify(repositorio, times(1)).save(any(Categoria.class));
    }
}
