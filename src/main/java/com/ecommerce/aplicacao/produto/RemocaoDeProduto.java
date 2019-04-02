package com.ecommerce.aplicacao.produto;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class RemocaoDeProduto implements RemovedorDeProduto {

    private ConsultorDeProduto consultaDeProduto;
    private ProdutoRepositorio repositorio;

    @Autowired
    public RemocaoDeProduto(ProdutoRepositorio repositorio, ConsultorDeProduto consultaDeProduto) {
        this.repositorio = repositorio;
        this.consultaDeProduto = consultaDeProduto;
	}

	@Override
    public void remover(long id) throws ExcecaoDeAplicacao, NotFoundException {
        Produto produtoArmazenado = consultaDeProduto.obterPor(id);

        repositorio.delete(produtoArmazenado);
    }
}