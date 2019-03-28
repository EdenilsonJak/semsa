package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.repository.filter.LeitoFilter;

public class LeitoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Leito guardar(Leito leito){
		return manager.merge(leito);
	}
	
	public List<Leito> porNome(String nome){
		return this.manager.createQuery("from Leito where upper(nome) like :nome", Leito.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	
	public Leito porId(Long id){
		return this.manager.find(Leito.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<Leito> filtrados(LeitoFilter leitoFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Leito.class);
			
			if(StringUtils.isNotBlank(leitoFilter.getNome())){
				criteria.add(Restrictions.eq("nome", leitoFilter.getNome().toUpperCase()));
			}			
			if(leitoFilter.getClinica() != null){
				criteria.add(Restrictions.eq("clinica", leitoFilter.getClinica()));
			}
			if(leitoFilter.getFornecedor() != null){
				criteria.add(Restrictions.eq("hospital", leitoFilter.getFornecedor()));
			}
			if(StringUtils.isNotBlank(leitoFilter.getDescricao())){
				criteria.add(Restrictions.eq("descricao", leitoFilter.getDescricao()));
			}
			if(StringUtils.isNotBlank(leitoFilter.getSexo())){
				criteria.add(Restrictions.eq("sexo", leitoFilter.getSexo()));
			}
			if(StringUtils.isNotBlank(leitoFilter.getStatus())){
				criteria.add(Restrictions.eq("status", leitoFilter.getStatus()));
			}
			
			return criteria.addOrder(Order.asc("nome")).list();
	}

	
}
