package com.ecommerce.aplicacao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.CriacaoDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Test;

public class CriacaoDeCategoriaTeste {

    @Test
    public void devePersistirUmaCategoria() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        CategoriaRepositorio repositorio = mock(CategoriaRepositorio.class);
        Categoria categoriaArmazenada = CategoriaBuilder.umaCategoria()
            .comId(1)
            .comNome("Categoria A")
            .construir();
        when(repositorio.save(any(Categoria.class))).thenReturn(categoriaArmazenada);
        CriacaoDeCategoria criadorDeCategoria = new CriacaoDeCategoria(repositorio);
        String nomeDaCategoria = "Categoria A";
        CategoriaDto categoriaDto = new CategoriaDto(nomeDaCategoria);

        criadorDeCategoria.criar(categoriaDto);

        verify(repositorio, times(1)).save(any(Categoria.class));
    }
}
