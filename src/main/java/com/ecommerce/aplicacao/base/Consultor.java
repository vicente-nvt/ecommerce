package com.ecommerce.aplicacao.base;

import java.util.List;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

import javassist.NotFoundException;

public interface Consultor<T> {

    ObjetoDto<T> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException;
	List<ObjetoDto<T>> consultarTodos();
    T obterPor(long id) throws NotFoundException;
}