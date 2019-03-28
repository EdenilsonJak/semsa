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

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.vo.MesAnoVO;
import com.ejsistemas.semsa.repository.filter.InternacaoFilter;

public class InternacaoRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Internacao guardar(Internacao internacao){
		return this.manager.merge(internacao);
	}
	
	public Internacao porId(Long id){
		return this.manager.find(Internacao.class, id);
	}
	
	public MesAnoVO consultaViaSql(String sql){
		return (MesAnoVO) this.manager.createNativeQuery(sql);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Internacao> filtradosOrderDesc(InternacaoFilter internacaoFilter) {
		
			
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Internacao.class);

				criteria.createCriteria("leito", Criteria.INNER_JOIN);
				criteria.createCriteria("cid", Criteria.LEFT_JOIN);
				criteria.createCriteria("usuario", Criteria.INNER_JOIN);
				criteria.createCriteria("paciente", criteria.INNER_JOIN);
			
			if(StringUtils.isNotBlank(internacaoFilter.getStatus())){
				criteria.add(Restrictions.eq("status", internacaoFilter.getStatus()));
			}
			
			return criteria.addOrder(Order.desc("dataInternacao")).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Internacao> filtradoOcupados(InternacaoFilter internacaoFilter) {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Internacao.class);
			
			if(StringUtils.isNotBlank(internacaoFilter.getStatus())){
				criteria.add(Restrictions.eq("status", internacaoFilter.getStatus()));
			}
			if(internacaoFilter.getPaciente() != null){
				criteria.add(Restrictions.eq("paciente", internacaoFilter.getPaciente()));
			}
			
			return criteria.addOrder(Order.desc("dataInternacao")).list();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Internacao> internacaoPrincipal(InternacaoFilter internacaoFilter){
		Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Internacao.class)
					
					// fazemos uma associaÃ§Ã£o (join) com leito e nomeamos como "l"
				.createAlias("leito", "l");
			criteria.add(Restrictions.isNull("internacao"));
			
			if(internacaoFilter.getHospital() != null){
				// acessamos hospital associado ao leito e por sua vez a Internação pelo alias "l", criado anteriormente
				criteria.add(Restrictions.eq("l.hospital", internacaoFilter.getHospital()));
			}
			if(internacaoFilter.getLeito() != null){
				// acessamos o leito associado a Internação pelo alias "l", criado anteriormente
				criteria.add(Restrictions.eq("l", internacaoFilter.getLeito()));
			}
			if(internacaoFilter.getClinica() != null){
				criteria.add(Restrictions.eq("l.clinica", internacaoFilter.getClinica()));
			}
			if(internacaoFilter.getPaciente() != null){
				criteria.add(Restrictions.eq("paciente", internacaoFilter.getPaciente()));
			}
						
			return criteria.addOrder(Order.desc("dataInternacao")).list();
			
			
	}
	
	@SuppressWarnings("unchecked")
	public List<Internacao> internacaoHistorico(InternacaoFilter internacaoFilter){
		Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Internacao.class);
					
				/*	fazemos uma associaÃ§Ã£o (join) com leito e nomeamos como "l"
				.createAlias("internacao", "i");*/
		
			if(internacaoFilter.getInternacao() != null){
				criteria.add(Restrictions.eq("internacao", internacaoFilter.getInternacao()));
			}
			
			return criteria.addOrder(Order.asc("dataInternacao")).list();
			
			
	}
	
}
