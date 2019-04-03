package com.ecommerce.aplicacao.cliente;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
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
    private ConsultorDeCliente consultorDeCliente;

    @Autowired
    public EdicaoDeCliente(ClienteRepositorio repositorio, ConsultorDeCliente consultorDeCliente) {
        this.repositorio = repositorio;
        this.consultorDeCliente = consultorDeCliente;
	}

	@Override
    public void editar(long id, CadastroDto<Cliente> dto) throws ExcecaoDeAplicacao, NotFoundException {

        CadastroDeClienteDto clienteDto = (CadastroDeClienteDto) dto;
        CadastroDeEnderecoDto enderecoDto = clienteDto.getEndereco();

        Cliente clienteArmazenado = consultorDeCliente.obterPor(id);

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