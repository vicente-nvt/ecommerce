package com.ecommerce.aplicacao.base;

import com.ecommerce.aplicacao.ExcecaoDeAplicacao;
import com.ecommerce.dominio.ExcecaoDeDominio;

import javassist.NotFoundException;

public interface Criador<T> {

    long criar(ObjetoDto<T> dto) throws ExcecaoDeAplicacao, ExcecaoDeDominio, NotFoundException;
}