package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.repository.filter.PacienteFilter;

public class PacienteRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Paciente guardar(Paciente Paciente){
		return manager.merge(Paciente);
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> porValores(String nome){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Paciente.class);
		
		Criterion name = Restrictions.like("nome", nome, MatchMode.ANYWHERE);
		Criterion sus = Restrictions.like("sus", nome, MatchMode.ANYWHERE);
		LogicalExpression orExp = Restrictions.or(name, sus);
		criteria.add(orExp);
		
		return criteria.addOrder(Order.asc("id_cliente")).list();
		
	}
	
	
	
	public List<Paciente> porNome(String nome){
		return this.manager.createQuery("from Paciente where upper(nome) like :nome", Paciente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public Paciente porCNS(String sus){
		try{
			return this.manager.createQuery("from Paciente where upper(sus) like :sus", Paciente.class)
					.setParameter("sus", sus.toUpperCase()).getSingleResult();
			
		}
		catch (NoResultException e){
			
		}
		return new Paciente();
	}

	public Paciente porId(Long id) {
		return this.manager.find(Paciente.class, id);
	}

	
	public List<Paciente> listarTodos(){
		return this.manager.createNamedQuery("Paciente.buscarPacientes", Paciente.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> filtrados(PacienteFilter pacienteFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Paciente.class);
			
			if(StringUtils.isNotBlank(pacienteFilter.getNome())){
				criteria.add(Restrictions.ilike("nome", pacienteFilter.getNome(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(pacienteFilter.getNome_mae())){
				criteria.add(Restrictions.ilike("nome_mae", pacienteFilter.getNome_mae(), MatchMode.ANYWHERE));
			}
			if(StringUtils.isNotBlank(pacienteFilter.getCns())){
				criteria.add(Restrictions.ilike("sus", pacienteFilter.getCns(), MatchMode.ANYWHERE));
			}			
			if(StringUtils.isNotBlank(pacienteFilter.getCpf())){
				criteria.add(Restrictions.ilike("cpf", pacienteFilter.getCpf(), MatchMode.ANYWHERE));
			}
			if(pacienteFilter.getData_nascimento() != null){
				criteria.add(Restrictions.ge("dt_nascimento", pacienteFilter.getData_nascimento()));
			}
			return criteria.addOrder(Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> porNomeCNS(String campos) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Paciente.class);
		
		Criterion nome = Restrictions.like("nome", campos, MatchMode.ANYWHERE);
		Criterion cns = Restrictions.like("sus", campos, MatchMode.ANYWHERE);
		
		LogicalExpression orExp = Restrictions.or(nome, cns);
		
		criteria.add(orExp);
		
		return criteria.list();
		
	}
}
