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

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.TipoEstabelecimento;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;

public class FornecedorRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Fornecedor guardar(Fornecedor fornecedor){
		return manager.merge(fornecedor);
	}
	
	public Fornecedor guardarHospital(Fornecedor fornecedor){
		return manager.merge(fornecedor);
	}

	public List<Fornecedor> porNome(String nome){
		return this.manager.createQuery("from Fornecedor where upper(fornecedor) like :fornecedor", Fornecedor.class)
				.setParameter("fornecedor", nome.toUpperCase() + "%").getResultList();
	}

	public Fornecedor porId(Long id) {
		return this.manager.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> filtrados(FornecedorFilter fornecedorFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Fornecedor.class);
			criteria.add(Restrictions.isNull("status"));
			
			if(StringUtils.isNotBlank(fornecedorFilter.getCNPJ())){
				criteria.add(Restrictions.ilike("cnpj", fornecedorFilter.getCNPJ(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFantasia())){
				criteria.add(Restrictions.ilike("fantasia", fornecedorFilter.getFantasia(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFornecedor())){
				criteria.add(Restrictions.ilike("fornecedor", fornecedorFilter.getFornecedor(), MatchMode.ANYWHERE));
			}			
			return criteria.addOrder(Order.asc("fornecedor")).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> hospitalFiltrados(FornecedorFilter fornecedorFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Fornecedor.class);
			criteria.add(Restrictions.eq("tipoEstabelecimento", TipoEstabelecimento.HOSPITAL));
			
			if(StringUtils.isNotBlank(fornecedorFilter.getCNPJ())){
				criteria.add(Restrictions.ilike("cnpj", fornecedorFilter.getCNPJ(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFantasia())){
				criteria.add(Restrictions.ilike("fantasia", fornecedorFilter.getFantasia(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(fornecedorFilter.getFornecedor())){
				criteria.add(Restrictions.ilike("fornecedor", fornecedorFilter.getFornecedor(), MatchMode.ANYWHERE));
			}
			return criteria.addOrder(Order.asc("fornecedor")).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> porFornecedorFantasiaHospital(String campos) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		Criterion fornecedor = Restrictions.like("fornecedor", campos, MatchMode.ANYWHERE);
		Criterion fantasia = Restrictions.like("fantasia", campos, MatchMode.ANYWHERE);
		
		criteria.add(Restrictions.eq("tipoEstabelecimento", TipoEstabelecimento.HOSPITAL));

		LogicalExpression orExp = Restrictions.or(fornecedor, fantasia);
		
		criteria.add(orExp);
		
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> porFornecedorFantasia(String campos) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		Criterion centro = Restrictions.eq("tipoEstabelecimento", TipoEstabelecimento.CENTROESPECIALIZADO);
		Criterion hospital = Restrictions.eq("tipoEstabelecimento", TipoEstabelecimento.HOSPITAL);
				
		Criterion fornecedor = Restrictions.like("fornecedor", campos, MatchMode.ANYWHERE);
		Criterion fantasia = Restrictions.like("fantasia", campos, MatchMode.ANYWHERE);
		
		LogicalExpression orExp = Restrictions.or(fornecedor, fantasia);
		LogicalExpression orTipo = Restrictions.or(centro, hospital);
		
		criteria.add(orExp);
		criteria.add(orTipo);
		
		return criteria.list();
		
	}

}
