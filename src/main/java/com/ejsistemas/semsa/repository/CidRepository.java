package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Cid10;

public class CidRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cid10 porId(Long id){
		return manager.find(Cid10.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cid10> porNomes(String str){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cid10.class);
		
		Criterion descricao = Restrictions.like("descricao", str, MatchMode.ANYWHERE);
		Criterion subcat = Restrictions.like("subcat", str, MatchMode.ANYWHERE);
		
		LogicalExpression orExp = Restrictions.or(descricao, subcat);
		
		criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("subcat")).list();
	}
	
}
