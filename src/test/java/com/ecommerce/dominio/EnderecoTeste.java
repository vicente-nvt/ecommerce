package com.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import com.ecommerce.builders.EnderecoBuilder;
import com.ecommerce.dominio.objetosdevalor.Endereco;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EnderecoTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    @Test
    public void deveCriarUmEnderecoComRua() throws ExcecaoDeDominio {
        String ruaEsperada = "Rua A";

        Endereco endereco = EnderecoBuilder.umEndereco().comRua(ruaEsperada).construir();

        assertEquals(ruaEsperada, endereco.getRua());
    }

    @Test
    public void naoDeveCriarUmEnderecoComRuaInvalida() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Rua inválida");
        String ruaInvalida = " ";

        EnderecoBuilder.umEndereco().comRua(ruaInvalida).construir();
    }

    @Test
    public void deveCriarUmEnderecoComCidade() throws ExcecaoDeDominio {
        String cidadeEsperada = "Cidade A";

        Endereco endereco = EnderecoBuilder.umEndereco().comCidade(cidadeEsperada).construir();

        assertEquals(cidadeEsperada, endereco.getCidade());
    }

    @Test
    public void naoDeveCriarUmEnderecoComCidadeInvalida() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Cidade inválida");
        String cidadeInvalida = " ";
        
        EnderecoBuilder.umEndereco().comCidade(cidadeInvalida).construir();
    }

    @Test
    public void deveCriarUmEnderecoComBairro() throws ExcecaoDeDominio {
        String bairroEsperado = "Bairro A";

        Endereco endereco = EnderecoBuilder.umEndereco().comBairro(bairroEsperado).construir();

        assertEquals(bairroEsperado, endereco.getBairro());
    }

    @Test
    public void naoDeveCriarUmEnderecoComBairroInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Bairro inválido");
        String bairroInvalido = " ";
        
        EnderecoBuilder.umEndereco().comBairro(bairroInvalido).construir();
    }

    @Test
    public void deveCriarUmEnderecoComCep() throws ExcecaoDeDominio {
        String cepEsperado = "11222-333";

        Endereco endereco = EnderecoBuilder.umEndereco().comCep(cepEsperado).construir();

        assertEquals(cepEsperado, endereco.getCep());
    }

    @Test
    public void naoDeveCriarUmEnderecoComCEPInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Cep inválido");
        String cepInvalido = " ";
        
        EnderecoBuilder.umEndereco().comCep(cepInvalido).construir();
    }

    @Test
    public void deveCriarUmEnderecoComEstado() throws ExcecaoDeDominio {
        String estadoEsperado = "Estado A";

        Endereco endereco = EnderecoBuilder.umEndereco().comEstado(estadoEsperado).construir();

        assertEquals(estadoEsperado, endereco.getEstado());
    }

    @Test
    public void naoDeveCriarUmEnderecoComEstadoInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Estado inválido");
        String estadoInvalido = " ";
        
        EnderecoBuilder.umEndereco().comEstado(estadoInvalido).construir();
    }
}
