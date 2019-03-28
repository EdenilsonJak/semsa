package com.ejsistemas.semsa.event;

import com.ejsistemas.semsa.model.Requisicao;

public class RequisicaoAlteradoEvent {
	
	private Requisicao requisicao;
	
	public RequisicaoAlteradoEvent(Requisicao requisicao){
		this.requisicao = requisicao;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	

}
