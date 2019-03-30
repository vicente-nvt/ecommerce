package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

import javassist.NotFoundException;

public interface Editor<T> {

    void editar(ObjetoDto<T> dto) throws ExcecaoDeAplicacao, NotFoundException;
}