package com.ejsistemas.semsa.event;

import com.ejsistemas.semsa.model.Internacao;

public class InternaAlteradoEvent {

	private Internacao internacao;
	
	public InternaAlteradoEvent(Internacao internacao){
		this.internacao = internacao;
	}
	
	public Internacao getInternacao() {
		return internacao;
	}
}
