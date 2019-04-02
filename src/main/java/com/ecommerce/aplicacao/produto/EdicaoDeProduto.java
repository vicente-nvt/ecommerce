package com.ecommerce.aplicacao.produto;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.aplicacao.categoria.ConsultaDeCategoria;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class EdicaoDeProduto implements EditorDeProduto {

    private ProdutoRepositorio repositorio;
    private ConsultaDeProduto consultaDeProduto;
    private ConsultaDeCategoria consultaDeCategoria;

    @Autowired
    public EdicaoDeProduto(ProdutoRepositorio repositorio, ConsultaDeProduto consultaDeProduto,
        ConsultaDeCategoria consultaDeCategoria) {
        this.repositorio = repositorio;
        this.consultaDeProduto = consultaDeProduto;
        this.consultaDeCategoria = consultaDeCategoria;
	}

	@Override
    public void editar(ObjetoDto<Produto> dto) throws ExcecaoDeAplicacao, NotFoundException {
        ProdutoDto produtoDto = (ProdutoDto) dto;
        CategoriaDto categoriaDto = produtoDto.getCategoria();

        Produto produtoEncontrado = consultaDeProduto.obterPor(produtoDto.getId());

        try {
            produtoEncontrado.alterarNome(produtoDto.getNome());
            produtoEncontrado.ajustarPreco(produtoDto.getPreco());
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }

        produtoEncontrado.definirQuantidade(produtoDto.getQuantidade());
        produtoEncontrado.mudarFoto(produtoDto.getFoto());
        produtoEncontrado.trocarCategoria( 
            categoriaDto.getId() > 0 
            ? consultaDeCategoria.obterPor(categoriaDto.getId())
            : null);

        repositorio.save(produtoEncontrado);
    }
}