package com.ejsistemas.semsa.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.Receituario;

public class RecepcaoReceituarioRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Receituario guardar(Receituario receituario){
		return this.manager.merge(receituario);
	}
	
	public Receituario porId(Long id){
		return this.manager.find(Receituario.class, id);
	}
	
}
