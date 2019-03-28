package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.UnidadeMedida;

public class UnidadeMedidaRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public UnidadeMedida porId(Long id){
		return this.manager.find(UnidadeMedida.class, id);
	}
	
	public List<UnidadeMedida> unidadeMedidas(){
		return this.manager.createQuery("from UnidadeMedida", UnidadeMedida.class)
				.getResultList();
	}

}
