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
    private ConsultorDeCategoria consultorDeCategoria;

    @Autowired
    public EdicaoDeCategoria(CategoriaRepositorio repositorio,
        ConsultorDeCategoria consultorDeCategoria) {
        this.repositorio = repositorio;
        this.consultorDeCategoria = consultorDeCategoria;
    }

    @Override
    public void editar(ObjetoDto<Categoria> dto) throws ExcecaoDeAplicacao, NotFoundException {
        CategoriaDto categoriaDto = (CategoriaDto) dto;
        Categoria categoria = consultorDeCategoria.obterObjetoDeDominio(dto.getId());

        try {
            categoria.atualizarNome(categoriaDto.getNome());
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }
        
        repositorio.save(categoria);
    }
}