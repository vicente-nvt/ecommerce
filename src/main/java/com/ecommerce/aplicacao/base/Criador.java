package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;

public interface Criador<T> {

    long criar(ObjetoDto<T> dto) throws ExcecaoDeAplicacao;
}