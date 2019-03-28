package com.ejsistemas.semsa.repository.dvs;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.dvs.FornecedorDvs;
import com.ejsistemas.semsa.util.jpa.Dvs;

public class FornecedorDvsRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	@Dvs
	private EntityManager manager;
	
	public FornecedorDvs guardar(FornecedorDvs fornecedorDvs){
		return this.manager.merge(fornecedorDvs);
	}
	
	public FornecedorDvs porId(Long id){
		return this.manager.find(FornecedorDvs.class, id);
	}
	
}
