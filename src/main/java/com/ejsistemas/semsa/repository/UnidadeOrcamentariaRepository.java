package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public UnidadeOrcamentaria porId(Long id){
		return this.manager.find(UnidadeOrcamentaria.class, id);
	}
	
	public List<UnidadeOrcamentaria> porNome(String nome){
		return this.manager.createQuery("from UnidadeOrcamentaria where upper(nome) like :nome", UnidadeOrcamentaria.class)
				.setParameter("nome", nome.toUpperCase()+"%")
				.getResultList();
	}

}
