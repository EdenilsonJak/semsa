package com.ejsistemas.semsa.repository.dvs;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.dvs.AtividadeEconomica;
import com.ejsistemas.semsa.repository.filter.dvs.AtividadeFilter;
import com.ejsistemas.semsa.util.jpa.Dvs;

public class AtividadeRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Dvs
	private EntityManager manager;

	public AtividadeEconomica porId(Long id) {
		return this.manager.find(AtividadeEconomica.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<AtividadeEconomica> filtrados(AtividadeFilter atividadeFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(AtividadeEconomica.class);
			//criteria.add(Restrictions.isNull("status"));
			
			/*if(StringUtils.isNotBlank(fornecedorFilter.getCNPJ())){
				criteria.add(Restrictions.ilike("cnpj", fornecedorFilter.getCNPJ(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFantasia())){
				criteria.add(Restrictions.ilike("fantasia", fornecedorFilter.getFantasia(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFornecedor())){
				criteria.add(Restrictions.ilike("fornecedor", fornecedorFilter.getFornecedor(), MatchMode.ANYWHERE));
			}	*/		
			return criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AtividadeEconomica> porValores(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AtividadeEconomica.class);
		
	//	Criterion name = Restrictions.like("nome", nome, MatchMode.ANYWHERE);
		criteria.add(Restrictions.ilike("nmatividade", nome, MatchMode.ANYWHERE));
		//Criterion sus = Restrictions.like("nr_sus", nome, MatchMode.ANYWHERE);
		//LogicalExpression orExp = Restrictions.or(name);
		//criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("nmatividade")).list();
		
	}
}
