package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.ModalidadeLicitacao;

public class ModalidadeLicitacaoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<ModalidadeLicitacao> porNome(String nm_modalidade){
		return this.manager.createQuery("from ModalidadeLicitacao where upper(nm_modalidade) like :nm_modalidade", ModalidadeLicitacao.class)
				.setParameter("nm_modalidade", nm_modalidade.toUpperCase() + "%").getResultList();
	}

	public ModalidadeLicitacao porId(Long id) {
		return this.manager.find(ModalidadeLicitacao.class, id);
	}

	public List<ModalidadeLicitacao> raizes(){
		return manager.createQuery("from ModalidadeLicitacao", ModalidadeLicitacao.class).getResultList();
	}
}
