package com.java.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import com.java.ecommerce.builders.ClienteBuilder;
import com.java.ecommerce.builders.EnderecoBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClienteTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    @Test
    public void deveCriarUmClienteComNome() throws ExcecaoDeDominio {
        String nomeEsperado = "João";

        Cliente cliente = ClienteBuilder.umCliente().comNome(nomeEsperado).construir();

        assertEquals(nomeEsperado, cliente.getNome());
    }

    @Test
    public void naoDeveCriarUmClienteComNomeInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        String nomeInvalido = " ";

        ClienteBuilder.umCliente().comNome(nomeInvalido).construir();
    }

    @Test
    public void deveCriarUmClienteComEmail() throws ExcecaoDeDominio {
        String emailEsperado = "cliente1@email.com.br";

        Cliente cliente = ClienteBuilder.umCliente().comEmail(emailEsperado).construir();

        assertEquals(emailEsperado, cliente.getEmail());
    }

    @Test
    public void naoDeveCriarUmClienteComEmailInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("E-mail inválido");
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

    @Test
    public void naoDeveCriarUmClienteSemSenha() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Senha inválida");
        String senhaVazia = " ";

        ClienteBuilder.umCliente().comSenha(senhaVazia).construir();
    }

    @Test
    public void deveCriarUmClienteComEndereco() throws ExcecaoDeDominio {
        Endereco enderecoEsperado = EnderecoBuilder.umEndereco().construir();

        Cliente cliente = ClienteBuilder.umCliente().comEndereco(enderecoEsperado).construir();

        assertEquals(enderecoEsperado, cliente.getEndereco());
    }

    @Test
    public void naoDeveCriarUmClienteComEnderecoNulo() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Endereço inválido");
        Endereco enderecoInvalido = null;

        ClienteBuilder.umCliente().comEndereco(enderecoInvalido).construir();
    }
}