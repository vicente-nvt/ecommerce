package com.ecommerce.aplicacao.produto;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
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
    public void editar(long id, CadastroDto<Produto> dto) throws ExcecaoDeAplicacao, NotFoundException {
        CadastroDeProdutoDto produtoDto = (CadastroDeProdutoDto) dto;

        Produto produtoEncontrado = consultaDeProduto.obterPor(id);

        try {
            produtoEncontrado.alterarNome(produtoDto.getNome());
            produtoEncontrado.ajustarPreco(produtoDto.getPreco());
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }

        produtoEncontrado.definirQuantidade(produtoDto.getQuantidade());
        produtoEncontrado.mudarFoto(produtoDto.getFoto());
        produtoEncontrado.trocarCategoria( 
            produtoDto.getIdDaCategoria() > 0 
            ? consultaDeCategoria.obterPor(produtoDto.getIdDaCategoria())
            : null);

        repositorio.save(produtoEncontrado);
    }
}