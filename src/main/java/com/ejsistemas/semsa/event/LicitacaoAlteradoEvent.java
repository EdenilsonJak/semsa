package com.ejsistemas.semsa.event;

import com.ejsistemas.semsa.model.Licitacao;

public class LicitacaoAlteradoEvent {

	private Licitacao licitacao;

	public LicitacaoAlteradoEvent(Licitacao licitacao) {
		this.licitacao = licitacao;
	}

	public Licitacao getLicitacao() {
		return licitacao;
	}

	
	
}
