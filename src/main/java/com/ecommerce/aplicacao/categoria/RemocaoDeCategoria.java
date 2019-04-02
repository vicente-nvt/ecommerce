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
    private ConsultorDeCategoria consultorDeCategoria;

    @Autowired
    public RemocaoDeCategoria(CategoriaRepositorio repositorio, 
        ConsultorDeCategoria consultorDeCategoria) {
        this.repositorio = repositorio;
        this.consultorDeCategoria = consultorDeCategoria;
    }

    @Override
    public void remover(long id) throws ExcecaoDeAplicacao, NotFoundException {
        Categoria categoria = consultorDeCategoria.obterPor(id);

        repositorio.delete(categoria);
    }
}