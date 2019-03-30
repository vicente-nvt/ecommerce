package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.aplicacao.dtos.ObjetoDto;

public interface Criador<T> {
    long criar(ObjetoDto<T> dto) throws ExcecaoDeAplicacao;
}