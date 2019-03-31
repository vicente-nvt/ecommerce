package com.ecommerce.dominio;

import static org.junit.Assert.assertEquals;

import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.EnderecoBuilder;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;

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

    @Test
    public void deveAlterarONomeDoCliente() throws ExcecaoDeDominio {
        final String novoNome = "Novo Nome";
        Cliente cliente = ClienteBuilder.umCliente().construir();
        
        cliente.alterarNome(novoNome);

        assertEquals(novoNome, cliente.getNome());
    }

    @Test
    public void naoDeveAlterarNomeSeForInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Nome inválido");
        final String nomeInvalido = " ";
        Cliente cliente = ClienteBuilder.umCliente().construir();

        cliente.alterarNome(nomeInvalido);
    }

    @Test
    public void deveAlterarASenhaDoCliente() throws ExcecaoDeDominio {
        final String novaSenha = "novaSenha";
        final String senhaEsperada = "9f0342bd90b4ebb67464782b0b2ccb8a51be7908b6501e95b33348cac6fba7dd";
        Cliente cliente = ClienteBuilder.umCliente().construir();
        
        cliente.trocarSenha(novaSenha);

        assertEquals(senhaEsperada, cliente.getSenha());
    }

    @Test
    public void naoDeveAlterarASenhaSeForInvalida() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Senha inválida");
        final String senhaInvalida = " ";
        Cliente cliente = ClienteBuilder.umCliente().construir();

        cliente.trocarSenha(senhaInvalida);
    }

    @Test
    public void deveTrocarOEmailDoCliente() throws ExcecaoDeDominio {
        final String novoEmail = "novo@email.com";
        Cliente cliente = ClienteBuilder.umCliente().construir();
        
        cliente.trocarEmail(novoEmail);

        assertEquals(novoEmail, cliente.getEmail());
    }
    @Test
    public void naoDeveTrocarOEmailSeForInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("E-mail inválido");
        final String emailInvalido = " ";
        Cliente cliente = ClienteBuilder.umCliente().construir();

        cliente.trocarEmail(emailInvalido);
    }

    @Test
    public void deveMudarOEnderecoDoCliente() throws ExcecaoDeDominio {
        final Endereco novoEndereco = EnderecoBuilder.umEndereco().comRua("Rua Nova").construir();
        Cliente cliente = ClienteBuilder.umCliente().construir();
        
        cliente.mudarEndereco(novoEndereco);

        assertEquals(novoEndereco, cliente.getEndereco());
    }

    @Test
    public void naoDeveMudarOEnderecoSeForInvalido() throws ExcecaoDeDominio {
        excecaoEsperada.expect(ExcecaoDeDominio.class);
        excecaoEsperada.expectMessage("Endereço inválido");
        final Endereco enderecoInvalido = null;
        Cliente cliente = ClienteBuilder.umCliente().construir();

        cliente.mudarEndereco(enderecoInvalido);
   }
}