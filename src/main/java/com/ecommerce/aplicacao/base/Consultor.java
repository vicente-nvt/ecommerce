package com.ecommerce.aplicacao.base;

import java.util.List;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

import javassist.NotFoundException;

public interface Consultor<T> {

    ObjetoDto<T> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException;
    T obterObjetoDeDominio(long id) throws NotFoundException;
	List<ObjetoDto<T>> obterTodos();
}