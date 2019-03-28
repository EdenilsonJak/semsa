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

import com.ejsistemas.semsa.model.Procedimento;

public class ProcedimentoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Procedimento guardar(Procedimento Procedimento){
		return manager.merge(Procedimento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Procedimento> porValores(String valor){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Procedimento.class);
		
		Criterion codigo = Restrictions.like("co_procedimento", valor, MatchMode.ANYWHERE);
		Criterion nome = Restrictions.like("no_procedimento", valor, MatchMode.ANYWHERE);
		LogicalExpression orExp = Restrictions.or(codigo, nome);
		criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("co_procedimento")).list();
		
	}
	
	
	
	public List<Procedimento> porNome(String nome){
		return this.manager.createQuery("from Procedimento where upper(no_procedimento) like :nome", Procedimento.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public Procedimento porId(Long id) {
		return this.manager.find(Procedimento.class, id);
	}

	
	public List<Procedimento> listarTodos(){
		return this.manager.createNamedQuery("Procedimento.buscarProcedimentos", Procedimento.class).getResultList();
	}
}