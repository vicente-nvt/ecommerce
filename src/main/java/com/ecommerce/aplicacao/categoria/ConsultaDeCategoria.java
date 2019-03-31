package com.ecommerce.aplicacao.categoria;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class ConsultaDeCategoria implements ConsultorDeCategoria {

    private CategoriaRepositorio repositorio;

    @Autowired
    public ConsultaDeCategoria(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ObjetoDto<Categoria> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException {
        return mapearCategoriaDto(obterObjetoDeDominio(id));
    }

    private CategoriaDto mapearCategoriaDto(Categoria categoriaEncontrada) {
        CategoriaDto categoriaMapeada = new CategoriaDto();

        categoriaMapeada.setId(categoriaEncontrada.getId());
        categoriaMapeada.setNome(categoriaEncontrada.getNome());

        return categoriaMapeada;
    }

    @Override
    public Categoria obterObjetoDeDominio(long id) throws NotFoundException {
        Categoria categoriaEncontrada = repositorio.findById(id);

        if (categoriaEncontrada == null)
            throw new NotFoundException("Categoria não encontrada");

        return categoriaEncontrada;
    }

    @Override
    public List<ObjetoDto<Categoria>> obterTodos() {
        List<Categoria> todasAsCategorias = repositorio.findAll();
        return todasAsCategorias.stream()
            .map(categoria -> mapearCategoriaDto(categoria))
            .collect(Collectors.toList());
    }
}