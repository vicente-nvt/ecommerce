package com.java.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CategoriaTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();


    @Test
    public void deveCriarUmaCategoriaComNome() throws ExcecaoDeDominio {
        String descricaoEsperada = "Uma categoria";

        Categoria categoria = new Categoria(descricaoEsperada);

        assertEquals(descricaoEsperada, categoria.getNome());
    }

    @Test 
    public void naoDeveCriarUmaCategoriaSemNome() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        String nomeInvalido = " ";
        
        new Categoria(nomeInvalido);
    }
}