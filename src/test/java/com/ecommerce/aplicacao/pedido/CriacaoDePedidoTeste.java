package com.ecommerce.aplicacao.pedido;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.cliente.ClienteDto;
import com.ecommerce.aplicacao.cliente.ConsultorDeCliente;
import com.ecommerce.aplicacao.cliente.EnderecoDto;
import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.builders.PedidoBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.entidades.Pedido;
import com.ecommerce.infra.repositorio.PedidoRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class CriacaoDePedidoTeste {

    private final long idDoCliente = 19;
    private final String nomeDoCliente = "Cliente A";
    private final String emailDoCliente = "email@cliente.com";
    private final String senhaDoCliente = "senha123";
    private final String rua = "Rua A";
    private final String cidade = "Cidade A";
    private final String bairro = "Bairro A";
    private final String cep = "11223-444";
    private final String estado = "Estado A";

    @Test
    public void deveArmazenarUmPedido() throws ExcecaoDeDominio, ExcecaoDeAplicacao, NotFoundException {
        PedidoRepositorio repositorio = mock(PedidoRepositorio.class);
        ConsultorDeCliente consultorDeCliente = mock(ConsultorDeCliente.class);
        CriacaoDePedido criacaoDePedido = new CriacaoDePedido(repositorio, consultorDeCliente);
        EnderecoDto enderecoDto = new EnderecoDto(rua, cidade, bairro, cep, estado);
        ClienteDto clienteDto = new ClienteDto(idDoCliente, nomeDoCliente, emailDoCliente, senhaDoCliente, enderecoDto);
        CriacaoDePedidoDto pedidoDto = new CriacaoDePedidoDto(clienteDto);
        Pedido pedido = PedidoBuilder.umPedido().construir();
        Cliente cliente = ClienteBuilder.umCliente().comId(idDoCliente).construir();
        when(repositorio.save(any(Pedido.class))).thenReturn(pedido);
        when(consultorDeCliente.obterObjetoDeDominio(anyLong())).thenReturn(cliente);

        criacaoDePedido.criar(pedidoDto);

        verify(repositorio, times(1)).save(any(Pedido.class));
    }

}