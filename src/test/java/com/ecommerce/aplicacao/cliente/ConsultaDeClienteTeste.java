package com.ecommerce.aplicacao.cliente;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.EnderecoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javassist.NotFoundException;

public class ConsultaDeClienteTeste {

    @Rule
    public ExpectedException excecaoEsperada = ExpectedException.none();

    private final long id = 1;
    private final String nome = "Cliente A";
    private final String email = "a@email.com";
    private final String senha = "Senha A";
    private final String rua = "Rua A";
    private final String bairro = "Bairro A";
    private final String cidade = "Cidade A";
    private final String cep = "99999-000";
    private final String estado = "Estado A";

    private ClienteRepositorio repositorio;
    private ConsultaDeCliente consultaDeCliente;

    @Before
    public void inicializar() {
        repositorio = mock(ClienteRepositorio.class);
        consultaDeCliente = new ConsultaDeCliente(repositorio);
    }

    @Test
    public void deveConsultarUmCliente() throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        Endereco endereco = EnderecoBuilder.umEndereco().comRua(rua)
            .comEstado(estado).comCidade(cidade).comCep(cep)
            .comBairro(bairro).construir();
        Cliente cliente = ClienteBuilder.umCliente().comNome(nome)
            .comEmail(email).comSenha(senha).comEndereco(endereco)
            .comId(id).construir();
        final EnderecoDto enderecoDto = new EnderecoDto(rua, cidade, bairro, cep, estado);
        ClienteDto clienteEsperado = new ClienteDto(id, nome, email, cliente.getSenha(), enderecoDto);
        when(repositorio.findById(anyLong())).thenReturn(cliente);

        ClienteDto clienteEncontrado = (ClienteDto) consultaDeCliente.consultarPor(anyLong());

        assertTrue(clienteEsperado.equals(clienteEncontrado));
    }

    @Test
    public void naoDeveGerarResultadoQuandoOClienteNaoExistir() throws ExcecaoDeAplicacao, NotFoundException {
        excecaoEsperada.expect(NotFoundException.class);
        excecaoEsperada.expectMessage("Cliente não encontrado");
        when(repositorio.findById(anyLong())).thenReturn(null);

        consultaDeCliente.consultarPor(anyLong());
    }
}