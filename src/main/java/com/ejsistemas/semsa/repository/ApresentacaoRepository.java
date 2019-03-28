package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.Apresentacao;

public class ApresentacaoRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;
	
	public Apresentacao guardar(Apresentacao apresentacao){
		return manager.merge(apresentacao);
	}
	
	public Apresentacao porId(Long id){
		return manager.find(Apresentacao.class, id);
	}
	
	public List<Apresentacao> raizes(){
		return manager.createQuery("from Apresentacao", Apresentacao.class).getResultList();
	}
}
