package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

import javassist.NotFoundException;

public interface Editor<T> {

    void editar(long id, CadastroDto<T> dto) throws ExcecaoDeAplicacao, NotFoundException;
}