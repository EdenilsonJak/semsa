package com.ejsistemas.semsa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.repository.filter.LicitacaoFilter;

public class LicitacaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Licitacao guardar(Licitacao licitacao) {
		return this.manager.merge(licitacao);
	}

	@SuppressWarnings("unchecked")
	public List<Licitacao> filtrados(LicitacaoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Licitacao.class)
				// fazemos uma associação (join) com cliente e nomeamos como "c"
			.createAlias("tipolicitacao", "t")
				// fazemos uma associação (join) com vendedor e nomeamos como "v"
			.createAlias("modalidade", "m");
		
		if (filtro.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}
		
		if (filtro.getNumeroAte() != null) {
			// id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}
		
		if (filtro.getDataCadastroDe() != null) {
			criteria.add(Restrictions.ge("dataLicitacao", filtro.getDataCadastroDe()));
		}
		
		if (filtro.getDataCadastroAte() != null) {
			criteria.add(Restrictions.le("dataLicitacao", filtro.getDataCadastroAte()));
		}
		
		if (filtro.getNumeroProcesso() != null){
			criteria.add(Restrictions.le("nr_processo", filtro.getNumeroProcesso()));
		}
		
		if (StringUtils.isNotBlank(filtro.getTipoLicitacao())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "t", criado anteriormente
			criteria.add(Restrictions.ilike("t.nome", filtro.getTipoLicitacao(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeModalidade())) {
			// acessamos o nome da modalidade associado a licitacao pelo alias "m", criado anteriormente
			criteria.add(Restrictions.ilike("m.nm_modalidade", filtro.getNomeModalidade(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
	}

	public Licitacao porId(Long id) {
		return this.manager.find(Licitacao.class, id);
	}
	
	public Licitacao porChave(Long chave){
		try{
		
			return this.manager.createQuery("from Licitacao where upper(id) like :chave", Licitacao.class)
					.setParameter("chave", chave).getSingleResult();
		} catch(NoResultException e){
			return null;
		}
		
		
	}


}
