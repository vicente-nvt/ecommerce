package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.base.CadastroDto;
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
    public void editar(long id, CadastroDto<Categoria> dto) throws ExcecaoDeAplicacao, NotFoundException {
        CadastroDeCategoriaDto categoriaDto = (CadastroDeCategoriaDto) dto;
        Categoria categoria = consultorDeCategoria.obterPor(id);

        try {
            categoria.atualizarNome(categoriaDto.getNome());
        } catch (ExcecaoDeDominio e) {
            throw new ExcecaoDeAplicacao(e.getMessage());
        }
        
        repositorio.save(categoria);
    }
}