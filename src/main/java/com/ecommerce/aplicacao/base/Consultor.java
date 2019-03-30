package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.dtos.ObjetoDto;

import javassist.NotFoundException;

public interface Consultor<T> {

    ObjetoDto<T> consultarPor(long id) throws ExcecaoDeAplicacao, NotFoundException;
}