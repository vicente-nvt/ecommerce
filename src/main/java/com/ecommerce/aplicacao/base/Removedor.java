package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

import javassist.NotFoundException;

public interface Removedor<T> {

    void remover(long id) throws ExcecaoDeAplicacao, NotFoundException;
}