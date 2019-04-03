package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.CodificadorDeSenha;
import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriacaoDeCliente implements CriadorDeCliente {

    private ClienteRepositorio repositorio;
    private CodificadorDeSenha codificadorDeSenha;

    @Autowired
    public CriacaoDeCliente(ClienteRepositorio repositorio, CodificadorDeSenha codificadorDeSenha) {
        this.repositorio = repositorio;
        this.codificadorDeSenha = codificadorDeSenha;
    }

    @Override
    public long criar(ObjetoDto<Cliente> dto) throws ExcecaoDeAplicacao {
        ClienteDto clienteDto = (ClienteDto) dto;
        EnderecoDto enderecoDto = clienteDto.getEndereco();

        if (enderecoDto == null)
            throw new ExcecaoDeAplicacao("O endereço não foi informado");

        Cliente cliente;
        try {
            Endereco endereco = new Endereco(
                enderecoDto.getRua(),
                enderecoDto.getCidade(),
                enderecoDto.getBairro(),
                enderecoDto.getCep(),
                enderecoDto.getEstado());

            cliente = new Cliente(
                clienteDto.getNome(), 
                clienteDto.getEmail(), 
                codificadorDeSenha.codificar(clienteDto.getSenha()), 
                endereco);
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }

        Cliente clienteArmazenado = repositorio.save(cliente);
        return clienteArmazenado.getId();
    }
}