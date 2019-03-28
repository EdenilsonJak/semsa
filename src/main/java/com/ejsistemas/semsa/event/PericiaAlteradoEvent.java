package com.ejsistemas.semsa.event;

import com.ejsistemas.semsa.model.Pericia;

public class PericiaAlteradoEvent {

	private Pericia pericia;
	
	public PericiaAlteradoEvent(Pericia pericia){
		this.pericia = pericia;
	}
	
	public Pericia getPericia() {
		return pericia;
	}
}
