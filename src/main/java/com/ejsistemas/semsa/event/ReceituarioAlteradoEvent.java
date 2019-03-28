package com.ejsistemas.semsa.event;

import com.ejsistemas.semsa.model.Receituario;

public class ReceituarioAlteradoEvent {

	private Receituario receituario;
	
	public ReceituarioAlteradoEvent(Receituario receituario){
		this.receituario = receituario;
	}
	
	public Receituario getReceituario() {
		return receituario;
	}
}
