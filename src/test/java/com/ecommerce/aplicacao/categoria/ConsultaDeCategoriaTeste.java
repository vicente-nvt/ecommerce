package com.ecommerce.aplicacao.categoria;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

    private final long id = 10;
    private final String nomeDaCategoria = "Categoria A";
    private CategoriaRepositorio repositorio;
    private ConsultaDeCategoria consultaDeCategoria;
    private Categoria categoria;
    private Optional<Categoria> optionalCategoria;

    @Before
    public void inicializar() throws ExcecaoDeDominio {
        repositorio = mock(CategoriaRepositorio.class);
        consultaDeCategoria = new ConsultaDeCategoria(repositorio);
        categoria = CategoriaBuilder.umaCategoria()
            .comNome(nomeDaCategoria)
            .comId(id)
            .construir();
        optionalCategoria = Optional.of(categoria);
    }

    @Test
    public void deveConsultarUmaCategoria() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        CategoriaDto categoriaEsperada = new CategoriaDto(nomeDaCategoria);
        when(repositorio.findById(anyLong())).thenReturn(optionalCategoria);

        CategoriaDto categoriaEncontrada = (CategoriaDto) consultaDeCategoria.consultarPor(anyLong());

        assertEquals(categoriaEsperada.getNome(), categoriaEncontrada.getNome());
    }

    @Test
    public void deveObterUmaCategoria() throws NotFoundException {
        when(repositorio.findById(anyLong())).thenReturn(optionalCategoria);

        Categoria categoriaEncontrada = consultaDeCategoria.obterPor(id);

        assertEquals(categoria, categoriaEncontrada);
    }

    @Test
    public void naoDeveGerarResultadoQuandoACategoriaNaoExistir() throws ExcecaoDeAplicacao, NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Categoria não encontrada");
        when(repositorio.findById(anyLong())).thenReturn(Optional.empty());

        consultaDeCategoria.consultarPor(anyLong());
    }
}