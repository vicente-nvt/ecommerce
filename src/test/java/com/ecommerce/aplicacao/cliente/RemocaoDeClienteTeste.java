package com.ecommerce.aplicacao.cliente;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.builders.ClienteBuilder;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.junit.Test;

import javassist.NotFoundException;

public class RemocaoDeClienteTeste {

    @Test
    public void deveRemoverUmCliente() throws NotFoundException, ExcecaoDeDominio, ExcecaoDeAplicacao {
        ClienteRepositorio repositorio = mock(ClienteRepositorio.class);
        ConsultorDeCliente consultaDeCliente = mock(ConsultorDeCliente.class);
        RemocaoDeCliente remocaoDeCliente = new RemocaoDeCliente(repositorio, consultaDeCliente);
        Cliente clienteArmazenado = ClienteBuilder.umCliente().construir();
        doNothing().when(repositorio).delete(any(Cliente.class));
        when(consultaDeCliente.obterObjetoDeDominio(anyLong())).thenReturn(clienteArmazenado);

        remocaoDeCliente.remover(clienteArmazenado.getId());

        verify(repositorio, times(1)).delete(any(Cliente.class));
    }
}