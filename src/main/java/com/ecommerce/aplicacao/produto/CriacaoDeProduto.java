package com.ecommerce.aplicacao.produto;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.ConsultorDeCategoria;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class CriacaoDeProduto implements CriadorDeProduto {

    private ProdutoRepositorio repositorio;
    private ConsultorDeCategoria consultorDeCategoria;

    @Autowired
    public CriacaoDeProduto(ProdutoRepositorio repositorio, ConsultorDeCategoria consultorDeCategoria) {
        this.repositorio = repositorio;
        this.consultorDeCategoria = consultorDeCategoria;
    }

    @Override
    public long criar(ObjetoDto<Produto> dto) throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException {
        ProdutoDto produtoDto = (ProdutoDto) dto;

        long idDaCategoria = produtoDto.getCategoria().getId();
        Categoria categoria = consultorDeCategoria.obterPor(idDaCategoria);

        Produto produto = new Produto(
            produtoDto.getNome(),
            produtoDto.getPreco(),
            produtoDto.getQuantidade(),
            produtoDto.getFoto(),
            categoria
        );
        
        Produto produtoCriado = repositorio.save(produto);
        return produtoCriado.getId();
    }
}