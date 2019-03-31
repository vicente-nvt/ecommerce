package com.ecommerce.aplicacao.categoria;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultaDeCategoria;
import com.ecommerce.builders.CategoriaBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class ConsultaDeCategoriaTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private CategoriaRepositorio repositorio;
    private ConsultaDeCategoria consultaDeCategoria;

    @Before
    public void inicializar() {
        repositorio = mock(CategoriaRepositorio.class);
        consultaDeCategoria = new ConsultaDeCategoria(repositorio);
    }

    @Test
    public void deveConsultarUmaCategoria() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        final String nomeDaCategoria = "Categoria A";
        CategoriaDto categoriaEsperada = new CategoriaDto(nomeDaCategoria);
        Categoria categoria = CategoriaBuilder.umaCategoria()
            .comNome(nomeDaCategoria)
            .comId(10)
            .construir();
        when(repositorio.findById(anyLong())).thenReturn(categoria);

        CategoriaDto categoriaEncontrada = (CategoriaDto) consultaDeCategoria.consultarPor(anyLong());

        assertEquals(categoriaEsperada.getNome(), categoriaEncontrada.getNome());
    }

    @Test
    public void naoDeveGerarResultadoQuandoACategoriaNaoExistir() throws ExcecaoDeAplicacao, NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Categoria não encontrada");
        when(repositorio.findById(anyLong())).thenReturn(null);

        consultaDeCategoria.consultarPor(anyLong());
    }
}