package com.java.ecommerce.dominio;

import java.util.ArrayList;
import java.util.List;

public class Validacao {

    private List<String> mensagens;

    public Validacao() {
        this.mensagens = new ArrayList<String>();
    }

    public static Validacao validar() {
		return new Validacao();
	}

	public Validacao quando(boolean condicao, String mensagem) {
		if (condicao)
			this.mensagens.add(mensagem);
		return this;
	}

	public void disparar() throws ExcecaoDeDominio {
		if (mensagens.size() > 0)
			throw new ExcecaoDeDominio(mensagens.toString());
	}
}
