package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;

public class ClinicaRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Clinica guardar(Clinica clinica){
		return manager.merge(clinica);
	}
	
	public Clinica porId(Long id){
		return manager.find(Clinica.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Clinica> filtrados(ClinicaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Clinica.class);
		
		//if(StringUtils.isNotBlank(filtro.getSku())){
	//	criteria.add(Restrictions.eq("sku", filtro.getSku()));
	//	}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
}
