package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.Solicitacao;


public class SolicitaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Solicitacao porId(Long id){
		return manager.find(Solicitacao.class, id);
	}
	
	public List<Solicitacao> raizes(){
		return manager.createQuery("from Solicitacao where solicitaPai is null",
				Solicitacao.class).getResultList();
	}
	
	public List<Solicitacao> solicitacaoDe(Solicitacao solicitacaoPai){
		return manager.createQuery("from Solicitacao where solicitaPai = :raiz order by descricao",
				Solicitacao.class).setParameter("raiz", solicitacaoPai).getResultList();
	}
}
