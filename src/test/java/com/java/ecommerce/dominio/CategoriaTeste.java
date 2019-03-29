package com.java.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CategoriaTeste {

    @Test
    public void deveCriarUmaCategoriaComDescricao() {
        String descricaoEsperada = "Uma categoria";

        Categoria categoria = new Categoria(descricaoEsperada);

        assertEquals(descricaoEsperada, categoria.getNome());
    }
}