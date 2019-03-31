package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Cliente;
import com.ecommerce.dominio.objetosdevalor.Endereco;
import com.ecommerce.infra.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class EdicaoDeCliente implements EditorDeCliente {

    private ClienteRepositorio repositorio;

    @Autowired
    public EdicaoDeCliente(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
	}

	@Override
    public void editar(ObjetoDto<Cliente> dto) throws ExcecaoDeAplicacao, NotFoundException {

        ClienteDto clienteDto = (ClienteDto) dto;
        EnderecoDto enderecoDto = clienteDto.getEndereco();

        Cliente clienteArmazenado = repositorio.findById(clienteDto.getId());

        if (clienteArmazenado == null)
            throw new NotFoundException("Cliente não encontrado");

        try {
            Endereco novoEndereco = new Endereco(
                enderecoDto.getRua(),
                enderecoDto.getCidade(),
                enderecoDto.getBairro(),
                enderecoDto.getCep(),
                enderecoDto.getEstado()
            );
            clienteArmazenado.alterarNome(clienteDto.getNome());
            clienteArmazenado.trocarEmail(clienteDto.getEmail());
            clienteArmazenado.trocarSenha(clienteDto.getSenha());
            clienteArmazenado.mudarEndereco(novoEndereco);
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }

        repositorio.save(clienteArmazenado);
    }
}