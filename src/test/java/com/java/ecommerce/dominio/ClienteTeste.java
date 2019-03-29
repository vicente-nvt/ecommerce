package com.java.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import com.java.ecommerce.builders.ClienteBuilder;
import com.java.ecommerce.builders.EnderecoBuilder;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeDominio;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeEmailInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeEnderecoInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeNomeInvalido;
import com.java.ecommerce.dominio.excecoes.ExcecaoDeSenhaInvalida;

import org.junit.Test;

public class ClienteTeste {

    @Test
    public void deveCriarUmClienteComNome() throws ExcecaoDeDominio {
        String nomeEsperado = "João";

        Cliente cliente = ClienteBuilder.umCliente().comNome(nomeEsperado).construir();

        assertEquals(nomeEsperado, cliente.getNome());
    }

    @Test(expected = ExcecaoDeNomeInvalido.class)
    public void naoDeveCriarUmClienteComNomeInvalido() throws ExcecaoDeDominio {
        String nomeInvalido = " ";

        ClienteBuilder.umCliente().comNome(nomeInvalido).construir();
    }

    @Test
    public void deveCriarUmClienteComEmail() throws ExcecaoDeDominio {
        String emailEsperado = "cliente1@email.com.br";

        Cliente cliente = ClienteBuilder.umCliente().comEmail(emailEsperado).construir();

        assertEquals(emailEsperado, cliente.getEmail());
    }

    @Test(expected = ExcecaoDeEmailInvalido.class)
    public void naoDeveCriarUmClienteComEmailInvalido() throws ExcecaoDeDominio {
        String emailInvalido = "email inválido";

        ClienteBuilder.umCliente().comEmail(emailInvalido).construir();
    }

    @Test
    public void deveCriarUmclienteComSenha() throws ExcecaoDeDominio {
        String senhaDecriptografada = "senha123";
        String senhaEsperada = "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251";

        Cliente cliente = ClienteBuilder.umCliente().comSenha(senhaDecriptografada).construir();

        assertEquals(senhaEsperada, cliente.getSenha());
    }

    @Test(expected = ExcecaoDeSenhaInvalida.class)
    public void naoDeveCriarUmClienteSemSenha() throws ExcecaoDeDominio {
        String senhaVazia = " ";

        ClienteBuilder.umCliente().comSenha(senhaVazia).construir();
    }

    @Test
    public void deveCriarUmClienteComEndereco() throws ExcecaoDeDominio {
        Endereco enderecoEsperado = EnderecoBuilder.umEndereco().construir();

        Cliente cliente = ClienteBuilder.umCliente().comEndereco(enderecoEsperado).construir();

        assertEquals(enderecoEsperado, cliente.getEndereco());
    }

    @Test(expected = ExcecaoDeEnderecoInvalido.class)
    public void naoDeveCriarUmClienteComEnderecoNulo() throws ExcecaoDeDominio {
        Endereco enderecoInvalido = null;

        ClienteBuilder.umCliente().comEndereco(enderecoInvalido).construir();
    }
}