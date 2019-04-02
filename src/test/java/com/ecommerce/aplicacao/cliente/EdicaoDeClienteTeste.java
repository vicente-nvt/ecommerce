package com.ecommerce.aplicacao.cliente;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.EnderecoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.junit.Test;
import org.mockito.InOrder;

import javassist.NotFoundException;

public class EdicaoDeClienteTeste {

    private final long id = 1;
    private final String nome = "Cliente A";
    private final String email = "a@email.com";
    private final String senha = "Senha A";
    private final String rua = "Rua A";
    private final String bairro = "Bairro A";
    private final String cidade = "Cidade A";
    private final String cep = "99999-000";
    private final String estado = "Estado A";

    @Test
    public void deveEditarUmCliente() throws ExcecaoDeDominio, ExcecaoDeAplicacao, NotFoundException {
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        ConsultorDeCliente consultaDeCliente = mock(ConsultorDeCliente.class);
        InOrder emOrdem = inOrder(repositorio, consultaDeCliente);
        Cliente clienteArmazenado = ClienteBuilder.umCliente().comId(id).construir();
        Endereco endereco = EnderecoBuilder.umEndereco()
            .comRua(rua)
            .comBairro(bairro)
            .comCidade(cidade)
            .comCep(cep)
            .comEstado(estado)
            .construir();
        Cliente clienteAlterado = ClienteBuilder.umCliente()
            .comId(id)
            .comNome(nome)
            .comEmail(email)
            .comSenha(senha)
            .comEndereco(endereco)
            .construir();
        when(consultaDeCliente.obterPor(anyLong())).thenReturn(clienteArmazenado);
        when(repositorio.save(clienteArmazenado)).thenReturn(clienteAlterado);
        EnderecoDto enderecoDto = new EnderecoDto(rua, cidade, bairro, cep, estado);
        ClienteDto clienteDto = new ClienteDto(id, nome, email, senha, enderecoDto);
        EdicaoDeCliente edicaoDeCliente = new EdicaoDeCliente(repositorio, consultaDeCliente);

        edicaoDeCliente.editar(clienteDto);

        assertTrue(clienteAlterado.equals(clienteArmazenado));
        emOrdem.verify(consultaDeCliente, times(1)).obterPor(anyLong());
        emOrdem.verify(repositorio, times(1)).save(any(Cliente.class));
    }
}