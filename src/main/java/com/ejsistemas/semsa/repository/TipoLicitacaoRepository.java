package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.TipoLicitacao;

public class TipoLicitacaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public TipoLicitacao porId(Long id){
		return this.manager.find(TipoLicitacao.class, id);
	}
	
	public List<TipoLicitacao> raizes(){
		return manager.createQuery("from TipoLicitacao", TipoLicitacao.class).getResultList();
	}

}
