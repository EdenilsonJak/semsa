package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.model.Status_Movimento_Requisicao;
import com.ejsistemas.semsa.model.Status_Prioridade_Requisicao;
import com.ejsistemas.semsa.repository.filter.RequisicaoRecepcaoFilter;

public class ItemReceituarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	List<ItemReceituario> geral;
	List<ItemReceituario> listaNormal;
	List<ItemReceituario> listaJudicial;
	List<ItemReceituario> listaRiscoVida;
	List<ItemReceituario> listaDeficiente;
	List<ItemReceituario> listaIdoso;

	public ItemReceituario porId(Long id) {
		return this.manager.find(ItemReceituario.class, id);
	}

	public ItemReceituario guardar(ItemReceituario itemReceituario) {
		return this.manager.merge(itemReceituario);
	}

	public int getItemReceituarioTotalCount() {
		Query query = manager.createQuery("Select count(i.id) From ItemReceituario i");
		return ((Long) query.getSingleResult()).intValue();
	}

	public List<ItemReceituario> getItemReceituarioList(int start, int size, Map<String, Object> filters) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<ItemReceituario> criteriaQuery = cb.createQuery(ItemReceituario.class);
		Root<ItemReceituario> root = criteriaQuery.from(ItemReceituario.class);
		CriteriaQuery<ItemReceituario> select = criteriaQuery.select(root);

		if (filters != null && filters.size() > 0) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				String field = entry.getKey();
				Object value = entry.getValue();
				if (value == null) {
					continue;
				}

				Expression<String> expr = root.get(field).as(String.class);
				Predicate p = cb.like(cb.lower(expr), "%" + value.toString().toLowerCase() + "%");
				predicates.add(p);
			}
			if (predicates.size() > 0) {
				criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			}
		}

		TypedQuery<ItemReceituario> query = manager.createQuery(select);
		query.setFirstResult(start);
		query.setMaxResults(size);
		List<ItemReceituario> list = query.getResultList();
		return list;
	}
	
	
	
	public int getFilteredRowCount(Map<String, Object> filters) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<ItemReceituario> root = criteriaQuery.from(ItemReceituario.class);
        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));

        if (filters != null && filters.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }

                Expression<String> expr = root.get(field).as(String.class);
                Predicate p = cb.like(cb.lower(expr),
                        "%" + value.toString().toLowerCase() + "%");
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                criteriaQuery.where(cb.and(predicates.toArray
                        (new Predicate[predicates.size()])));
            }
        }
        Long count = manager.createQuery(select).getSingleResult();
        return count.intValue();
    }

	public List<ItemReceituario> montaLista() {
		geral = new ArrayList<>();
		listaNormal = listaEsperaNormal();
		listaJudicial = listaEsperaJudicial();
		listaRiscoVida = listaEsperaRiscodeVida();
		listaDeficiente = listaEsperaDeficiente();
		listaIdoso = listaEsperaIdoso();

		if (listaNormal.size() != 0) {
			geral = listaNormal;
		}
		if (listaJudicial.size() != 0) {
			imprimeInvestida(listaJudicial);
		}
		if (listaIdoso.size() != 0) {
			imprimeInvestida(listaIdoso);
		}
		if (listaDeficiente.size() != 0) {
			imprimeInvestida(listaDeficiente);
		}
		if (listaRiscoVida.size() != 0) {
			imprimeInvestida(listaRiscoVida);
		}
		return geral;
	}

	public void imprimeInvestida(Collection<? extends Object> collection) {
		List<Object> invertida = new ArrayList<Object>(collection);
		Collections.reverse(invertida);
		for (Object objeto : invertida) {
			geral.add(0, (ItemReceituario) objeto);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> listaEsperaNormal() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
				// Fazendo join, associação com receituario e nomeamos como "r"
	
				.createAlias("receituario.paciente", "r.p")
				.createAlias("medico", "m")
				.createAlias("receituario", "r")
				.createAlias("solicitacao", "s")
				.createAlias("unidadeSaude", "u");
				
		/*criteria.createCriteria("medico", Criteria.INNER_JOIN);
		criteria.createCriteria("solicitacao", Criteria.INNER_JOIN);
		criteria.createCriteria("unidadeSaude", Criteria.INNER_JOIN);*/
		
		
		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.add(Restrictions.eq("prioridade_status", Status_Prioridade_Requisicao.NORMAL));
		criteria.setFetchMode("receituario", FetchMode.JOIN);
		criteria.setFetchMode("receituario.usuarioEntrada", FetchMode.JOIN);

		return criteria.addOrder(Order.asc("r.data")).list();

	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> listaEsperaJudicial() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
		
				// Fazendo join, associação com receituario e nomeamos como "r"
				.createAlias("receituario.paciente", "r.p")
				.createAlias("medico", "m")
				.createAlias("receituario", "r")
				.createAlias("solicitacao", "s")
				.createAlias("unidadeSaude", "u");
		
		
		
		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.add(Restrictions.eq("prioridade_status", Status_Prioridade_Requisicao.ACAOJUDICIAL));
		criteria.setFetchMode("receituario", FetchMode.JOIN);
		criteria.setFetchMode("receituario.usuarioEntrada", FetchMode.JOIN);

		return criteria.addOrder(Order.asc("r.data")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> listaEsperaRiscodeVida() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
				
				// Fazendo join, associação com receituario e nomeamos como "r"
				
				.createAlias("receituario.paciente", "r.p")
				.createAlias("medico", "m")
				.createAlias("receituario", "r")
				.createAlias("solicitacao", "s")
				.createAlias("unidadeSaude", "u");
		
		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.add(Restrictions.eq("prioridade_status", Status_Prioridade_Requisicao.RISCODEVIDA));
		criteria.setFetchMode("receituario", FetchMode.JOIN);
		criteria.setFetchMode("receituario.usuarioEntrada", FetchMode.JOIN);

		return criteria.addOrder(Order.asc("r.data")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> listaEsperaDeficiente() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
				
				// Fazendo join, associação com receituario e nomeamos como "r"
				.createAlias("receituario.paciente", "r.p")
				.createAlias("medico", "m")
				.createAlias("receituario", "r")
				.createAlias("solicitacao", "s")
				.createAlias("unidadeSaude", "u");
		

		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.add(Restrictions.eq("prioridade_status", Status_Prioridade_Requisicao.DEFICIENTEFISICO));
		criteria.setFetchMode("receituario", FetchMode.JOIN);
		criteria.setFetchMode("receituario.usuarioEntrada", FetchMode.JOIN);

		return criteria.addOrder(Order.asc("r.data")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> listaEsperaIdoso() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
				
				// Fazendo join, associação com receituario e nomeamos como "r"
				.createAlias("receituario.paciente", "r.p")
				.createAlias("medico", "m")
				.createAlias("receituario", "r")
				.createAlias("solicitacao", "s")
				.createAlias("unidadeSaude", "u");
		
		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.add(Restrictions.eq("prioridade_status", Status_Prioridade_Requisicao.IDOSO));
		criteria.setFetchMode("receituario", FetchMode.JOIN);
		criteria.setFetchMode("receituario.usuarioEntrada", FetchMode.JOIN);

		return criteria.addOrder(Order.asc("r.data")).list();
	}

	@SuppressWarnings("unchecked")
	public List<ItemReceituario> consultaFilter(RequisicaoRecepcaoFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)

				// Faz join. associação com receituario e nomeamos com "r"
				.createAlias("receituario", "r");

		if (filter.getUnidade() != null) {
			criteria.add(Restrictions.eq("unidadeSaude", filter.getUnidade()));
		}

		if (filter.getPaciente() != null) {
			criteria.add(Restrictions.eq("r.paciente", filter.getPaciente()));
		}

		if (filter.getStatus() != null) {
			criteria.add(Restrictions.eq("prioridade_status", filter.getStatus()));
		}

		if (filter.getStatus_movimento() != null) {
			criteria.add(Restrictions.eq("movimento_status", filter.getStatus_movimento()));
		}

		if (filter.getDataInicioRequisicao() != null) {
			criteria.add(Restrictions.ge("r.data", filter.getDataInicioRequisicao()));
		}

		if (filter.getDataFimRequisicao() != null) {
			criteria.add(Restrictions.le("r.data", filter.getDataFimRequisicao()));
		}

		if (filter.getDataInicioRequisicaoMarcacao() != null) {
			criteria.add(Restrictions.ge("dataMarcacao", filter.getDataInicioRequisicaoMarcacao()));
		}
		if (filter.getDataFimRequisicaoMarcacao() != null) {
			criteria.add(Restrictions.le("dataMarcacao", filter.getDataFimRequisicaoMarcacao()));
		}

		return criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ItemReceituario> filtrados(RequisicaoRecepcaoFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		return criteria.addOrder(Order.asc("r.data")).list();
	}
	
	public int quantidadeFiltrados(RequisicaoRecepcaoFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	
	private Criteria criarCriteriaParaFiltro(RequisicaoRecepcaoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemReceituario.class)
					.createAlias("receituario", "r")
					.createAlias("solicitacao", "s");
		criteria.add(Restrictions.eq("movimento_status", Status_Movimento_Requisicao.ESPERA));
		criteria.setFetchMode("receituario", FetchMode.EAGER);
		
		if (filtro.getStatus()!= null) {
			criteria.add(Restrictions.eq("prioridade_status", filtro.getStatus()));
		}
		
		if(filtro.getSolicitacao() != null){
			criteria.add(Restrictions.eq("s.descricao", filtro.getSolicitacao().getDescricao()));
		}
		
		return criteria;
	}

}