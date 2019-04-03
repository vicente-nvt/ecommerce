package com.ecommerce.aplicacao.categoria;

import com.ecommerce.aplicacao.base.CadastroDto;
import com.ecommerce.dominio.entidades.Categoria;

public class CadastroDeCategoriaDto implements CadastroDto<Categoria> {

    private String nome;

    public CadastroDeCategoriaDto() {
    }

    public CadastroDeCategoriaDto(String nome) {
        this.nome = nome;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}