package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.repository.filter.RequisicaoFilter;

public class RequisicaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Requisicao guardar(Requisicao requisicao){
	
		return this.manager.merge(requisicao);
		
	}
	
	public Requisicao porId(Long id){
		
		return this.manager.find(Requisicao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Requisicao> filtrados(RequisicaoFilter requisicaoFilter) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Requisicao.class);
		
		if(requisicaoFilter.getCod() != null){
			criteria.add(Restrictions.eq("id", requisicaoFilter.getCod()));
		}
		
		if(requisicaoFilter.getDataInicio() != null){
			criteria.add(Restrictions.ge("dataRequisicao", requisicaoFilter.getDataInicio()));
		}
		
		if(requisicaoFilter.getDataFim() != null){
			criteria.add(Restrictions.le("dataRequisicao", requisicaoFilter.getDataFim()));
		}
		
		if (requisicaoFilter.getStatuses() != null && requisicaoFilter.getStatuses().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("status", requisicaoFilter.getStatuses()));
		}
		
		return criteria.addOrder(Order.desc("dataRequisicao")).list();
	}

}
