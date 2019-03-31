package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class RemocaoDeCliente implements RemovedorDeCliente {

    private ClienteRepositorio repositorio;
    private ConsultorDeCliente consultaDeCliente;

    public RemocaoDeCliente(ClienteRepositorio repositorio, ConsultorDeCliente consultaDeCliente) {
        this.repositorio = repositorio;
        this.consultaDeCliente = consultaDeCliente;
	}

	@Override
    public void remover(long id) throws ExcecaoDeAplicacao, NotFoundException {
        Cliente clienteArmazenado = consultaDeCliente.obterObjetoDeDominio(id);

        repositorio.delete(clienteArmazenado);
    }
}