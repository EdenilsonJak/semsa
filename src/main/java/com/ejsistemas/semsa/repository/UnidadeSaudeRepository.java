package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.model.UnidadeSaude;

public class UnidadeSaudeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public UnidadeSaude porId(Long id){
		return this.manager.find(UnidadeSaude.class, id);
	}
	
	public List<UnidadeSaude> todosUnidadedeSaude(){
		return this.manager.createQuery("from UnidadeSaude order by nome", UnidadeSaude.class).getResultList();
	}

	public List<UnidadeSaude> porNome(String nome) {
			return this.manager.createQuery("from UnidadeSaude where upper(nome) like :nome", UnidadeSaude.class)
					.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadeSaude> porValores(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UnidadeSaude.class);
		
		Criterion name = Restrictions.like("nome", nome, MatchMode.ANYWHERE);
		Disjunction orExp = Restrictions.or(name);
		criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
