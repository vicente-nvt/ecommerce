package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.ConsultorDeCategoria;
import com.ecommerce.aplicacao.dtos.CategoriaDto;
import com.ecommerce.aplicacao.dtos.ObjetoDto;
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
        Categoria categoriaEncontrada = repositorio.findById(id);

        if (categoriaEncontrada == null) 
            throw new NotFoundException("Categoria não encontrada");

        return mapearCategoriaDto(categoriaEncontrada);
    }

    private CategoriaDto mapearCategoriaDto(Categoria categoriaEncontrada) {
        CategoriaDto categoriaMapeada = new CategoriaDto();

        categoriaMapeada.setId(categoriaEncontrada.getId());
        categoriaMapeada.setNome(categoriaEncontrada.getNome());

        return categoriaMapeada;
    }
}