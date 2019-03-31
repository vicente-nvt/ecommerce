package com.ecommerce.aplicacao.produto;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.aplicacao.categoria.CategoriaDto;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.dominio.entidades.Produto;
import com.ecommerce.infra.repositorio.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class ConsultaDeProduto implements ConsultorDeProduto {

    private ProdutoRepositorio repositorio;

    @Autowired
    public ConsultaDeProduto(ProdutoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ObjetoDto<Produto> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException {
        return mapearProdutoDto(obterObjetoDeDominio(id));
    }

    private ProdutoDto mapearProdutoDto(Produto produto) {
        Categoria categoria = produto.getCategoria();
        CategoriaDto categoriaDto = null;

        if (categoria != null)
            categoriaDto = new CategoriaDto(categoria.getId(), categoria.getNome());

        ProdutoDto produtoDto = new ProdutoDto(produto.getId(), produto.getNome(), produto.getPreco(),
                produto.getQuantidade(), produto.getFoto(), categoriaDto);

        return produtoDto;
    }

    @Override
    public Produto obterObjetoDeDominio(long id) throws NotFoundException {
        Produto produtoEncontrado = repositorio.findById(id);

        if (produtoEncontrado == null)
            throw new NotFoundException("Produto não encontrado");

        return produtoEncontrado;
    }

    @Override
    public List<ObjetoDto<Produto>> obterTodos() {
        List<Produto> todosOsProdutos = repositorio.findAll();
        return todosOsProdutos.stream()
            .map(produto -> mapearProdutoDto(produto))
            .collect(Collectors.toList());
   }
}