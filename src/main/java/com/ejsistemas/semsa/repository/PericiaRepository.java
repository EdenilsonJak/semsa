package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.DisjunctionFragment;
import org.hibernate.type.DateType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Pericia;
import com.ejsistemas.semsa.model.Status_Movimento_Requisicao;
import com.ejsistemas.semsa.model.Status_Pericia;
import com.ejsistemas.semsa.repository.filter.PericiaFilter;
import com.ejsistemas.semsa.repository.filter.RequisicaoRecepcaoFilter;

public class PericiaRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Pericia guardar(Pericia pericia){
		return this.manager.merge(pericia);
	}
	
	public Pericia porId(Long id){
		return manager.find(Pericia.class, id);
	}
	
	public List<Pericia> listarTodos(){
		return manager.createNamedQuery("Pericia.buscarPericias", Pericia.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pericia> filtrados(PericiaFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		criteria.addOrder(Order.desc("dt_entrada"));
		return criteria.list();
	}
	
	public int quantidadeFiltrados(PericiaFilter filter){
		Criteria criteria = criarCriteriaParaFiltro(filter);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(PericiaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pericia.class)
					.createAlias("paciente", "p")
					.createAlias("medico", "m");
	
		//criteria.add(Restrictions.isNotNull("status"));
	
		/*Disjunction d = Restrictions.disjunction();
		d.add(Restrictions.eq("status", Status_Pericia.ENTREGUE));
		d.add(Restrictions.eq("status", Status_Pericia.PENDENTE));
		d.add(Restrictions.eq("status", Status_Pericia.RECEBIDO));
		
		criteria.add(d);*/
		
		if(filtro.getStatus() != null){
			criteria.add(Restrictions.eq("status", filtro.getStatus()));
		}
		
		if(filtro.getDataAntes() != null){
			//filtro.setDataAntes(new Date(filtro.getDataAntes().getTime()+ 24 * 60 * 60 * 1000));
			criteria.add(Restrictions.ge("dt_entrada", filtro.getDataAntes()));
		}
		
		if(filtro.getDataDepois() != null){
			//filtro.setDataDepois(new Date(filtro.getDataDepois().getTime() + 24 * 60 * 60 * 1000));
			criteria.add(Restrictions.sqlRestriction("date(this_.dt_entrada)<= ?", filtro.getDataDepois(), StandardBasicTypes.DATE));
			/*criteria.add(Restrictions.le("status", filtro.getDataDepois()));*/
		}
		
		if(filtro.getPaciente() != null){
			criteria.add(Restrictions.eq("paciente", filtro.getPaciente()));
		}
		
		if(filtro.getFornecedor() != null){
			criteria.add(Restrictions.eq("estabelecimento", filtro.getFornecedor()));
		}
		
		//criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		//criteria.setFetchMode("receituario", FetchMode.EAGER);
		
		/*if (filtro.getStatus()!= null) {
			criteria.add(Restrictions.eq("prioridade_status", filtro.getStatus()));
		}*/
		
		/*if(filtro.getSolicitacao() != null){
			criteria.add(Restrictions.eq("s.descricao", filtro.getSolicitacao().getDescricao()));
		}*/
		
		/*Disjunction dis = Restrictions.disjunction();
		dis.add(Restrictions.like("status", Status_Pericia.RECEBIDO));
		dis.add(Restrictions.like("status", Status_Pericia.PENDENTE));
		criteria.add(dis);*/
		
		return criteria;
	}

}
