package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriacaoDeCategoria implements CriadorDeCategoria {

    CategoriaRepositorio repositorio;

    @Autowired
    public CriacaoDeCategoria(CategoriaRepositorio categoriaRepositorio) {
        this.repositorio = categoriaRepositorio;
    }

    @Override
    public long criar(ObjetoDto<Categoria> dto) throws ExcecaoDeAplicacao {
        String nomeDaCategoria = ((CategoriaDto) dto).getNome();

        Categoria categoria;
        try {
            categoria = new Categoria(nomeDaCategoria);
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }

        Categoria categoriaArmazenada = repositorio.save(categoria);

        return categoriaArmazenada.getId();
    }
}