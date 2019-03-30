package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ObjetoDto;
import com.ecommerce.dominio.ExcecaoDeDominio;
import com.ecommerce.dominio.entidades.Categoria;
import com.ecommerce.infra.repositorio.CategoriaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javassist.NotFoundException;

@Component
public class EdicaoDeCategoria implements EditorDeCategoria {

    private CategoriaRepositorio repositorio;

    @Autowired
    public EdicaoDeCategoria(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void editar(ObjetoDto<Categoria> dto) throws ExcecaoDeAplicacao, NotFoundException {
        CategoriaDto categoriaDto = (CategoriaDto) dto;
        Categoria categoria = repositorio.findById(categoriaDto.getId());
        
        if (categoria == null)
            throw new NotFoundException("Categoria não encontrada");
        
        try {
            categoria.atualizarNome(categoriaDto.getNome());
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }
        
        repositorio.save(categoria);
    }

}