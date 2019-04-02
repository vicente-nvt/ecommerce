package com.ecommerce.aplicacao.cliente;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class ConsultaDeCliente implements ConsultorDeCliente {

    private ClienteRepositorio repositorio;

    @Autowired
    public ConsultaDeCliente(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ObjetoDto<Cliente> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException {
        return mapearClienteDto(obterPor(id));
    }

    private ClienteDto mapearClienteDto(Cliente clienteEncontrado) {
        Endereco endereco = clienteEncontrado.getEndereco();

        EnderecoDto enderecoDto = new EnderecoDto(
            endereco.getRua(), 
            endereco.getCidade(), 
            endereco.getBairro(),
            endereco.getCep(), 
            endereco.getEstado());

        ClienteDto clienteDto = new ClienteDto(
            clienteEncontrado.getId(),
            clienteEncontrado.getNome(),
            clienteEncontrado.getEmail(),
            clienteEncontrado.getSenha(),
            enderecoDto);

        return clienteDto;
    }

    @Override
    public Cliente obterPor(long id) throws NotFoundException {
        Cliente clienteEncontrado = repositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        return clienteEncontrado;
    }

    @Override
    public List<ObjetoDto<Cliente>> consultarTodos() {
        return repositorio.findAll().stream()
            .map(cliente -> mapearClienteDto(cliente))
            .collect(Collectors.toList());
    }
}