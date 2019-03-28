package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ejsistemas.semsa.model.Publicacao;

public class PublicacaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Publicacao> porNome(String nome){
		return this.manager.createQuery("from Publicacao where upper(nome) like :nome", Publicacao.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public Publicacao porId(Long id) {
		return this.manager.find(Publicacao.class, id);
	}

}
