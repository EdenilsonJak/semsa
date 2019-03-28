package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.ejsistemas.semsa.event.InternaAlteradoEvent;
import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.qualificador.edicao.InternaEdicao;

public class AltaPacienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@InternaEdicao
	private Internacao internacao;
	
	@Inject
	private Event<InternaAlteradoEvent> internacaoAlteradoEvent;
	
	public void internarPaciente(){
		this.internacaoAlteradoEvent.fire(new InternaAlteradoEvent(this.internacao));
	}
}
