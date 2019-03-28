package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.filter.MedicoFilter;

public class MedicoRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Medico guardar(Medico medico){
		return this.manager.merge(medico);
	}
	
	public Medico porId(Long id){
		return this.manager.find(Medico.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Medico> porValores(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Medico.class);
		
		Criterion name = Restrictions.like("nome", nome, MatchMode.ANYWHERE);
		Criterion sus = Restrictions.like("nr_sus", nome, MatchMode.ANYWHERE);
		LogicalExpression orExp = Restrictions.or(name, sus);
		criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
	
	public List<Medico> porNome(String nome){
		return this.manager.createQuery("from Medico where upper(nome) like :nome", Medico.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> filtrados(MedicoFilter medicoFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Medico.class);
			
			if(StringUtils.isNotBlank(medicoFilter.getNome())){
				criteria.add(Restrictions.ilike("nome", medicoFilter.getNome(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(medicoFilter.getCns())){
				criteria.add(Restrictions.ilike("nr_sus", medicoFilter.getCns(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(medicoFilter.getCpf())){
				criteria.add(Restrictions.ilike("cpf", medicoFilter.getCpf(), MatchMode.ANYWHERE));
			}
			return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Medico> buscarTodosMedicos(){
		return manager.createNamedQuery("Medico.buscarMedicos", Medico.class).getResultList();
	}

}
