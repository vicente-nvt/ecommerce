package com.ecommerce.aplicacao.cliente;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.EnderecoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.junit.Test;

public class CriacaoDeClienteTeste {

    private final long id = 10;
    private final String nome = "Cliente A";
    private final String email = "email@docliente.com";
    private final String senha = "senha123";
    private final String rua = "Rua A";
    private final String bairro = "Bairro A";
    private final String cidade = "Cidade A";
    private final String estado = "Estado A";
    private final String cep = "11222-333";

    @Test
    public void deveArmazenarUmCliente() throws ExcecaoDeDominio, ExcecaoDeAplicacao {
        final Endereco endereco = EnderecoBuilder.umEndereco()
            .comBairro(bairro).comRua(rua).comCidade(cidade)
            .comEstado(estado).comCep(cep).construir();
        Cliente clienteArmazenado = ClienteBuilder.umCliente()
            .comId(id).comNome(nome).comEmail(email).comSenha(senha)
            .comEndereco(endereco).construir();
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        when(repositorio.save(any(Cliente.class))).thenReturn(clienteArmazenado);
        CriacaoDeCliente criacaoDeCliente = new CriacaoDeCliente(repositorio);
        EnderecoDto enderecoDto = new EnderecoDto(rua, cidade, bairro, cep, estado);
        ClienteDto clienteDto = new ClienteDto(id, nome, email, senha, enderecoDto);

        criacaoDeCliente.criar(clienteDto);

        verify(repositorio, times(1)).save(any(Cliente.class));
    }
}