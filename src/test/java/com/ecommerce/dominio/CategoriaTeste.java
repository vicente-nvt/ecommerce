package com.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import com.ecommerce.dominio.entidades.Categoria;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CategoriaTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();


    @Test
    public void deveCriarUmaCategoriaComNome() throws ExcecaoDeDominio {
        String nomeEsperado = "Uma categoria";

        Categoria categoria = new Categoria(nomeEsperado);

        assertEquals(nomeEsperado, categoria.getNome());
    }

    @Test 
    public void naoDeveCriarUmaCategoriaSemNome() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        String nomeInvalido = " ";
        
        new Categoria(nomeInvalido);
    }

    @Test
    public void deveAtualizarONomeDaCategoria() throws ExcecaoDeDominio {
        String nomeNovo = "Novo nome";
        Categoria categoria = new Categoria("Nome antigo");

        categoria.atualizarNome(nomeNovo);

        assertEquals(nomeNovo, categoria.getNome());
    }

    @Test
    public void naoDeveAtualizarONomeParaUmNomeInválido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        String nomeInvalido = " ";
        Categoria categoria = new Categoria("Nome antigo");

        categoria.atualizarNome(nomeInvalido);
   }
}