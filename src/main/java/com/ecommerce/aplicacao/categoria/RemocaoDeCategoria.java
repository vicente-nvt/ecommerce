package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class RemocaoDeCategoria implements RemovedorDeCategoria {

    private CategoriaRepositorio repositorio;

    @Autowired
    public RemocaoDeCategoria(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void remover(long id) throws ExcecaoDeAplicacao, NotFoundException {
        Categoria categoria = repositorio.findById(id);

        if (categoria == null)
            throw new NotFoundException("Categoria inexistente");

        repositorio.delete(categoria);
    }
}