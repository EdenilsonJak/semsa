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

import com.ejsistemas.semsa.model.ItemLicitacao;

public class ItemLicitacaoRepository implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public ItemLicitacao porId(Long id){
		return this.manager.find(ItemLicitacao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ItemLicitacao> porNome(String nome, Long chave) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ItemLicitacao.class, "itemLicitacao")
		
				// fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("itemLicitacao.produto", "p")
				.createAlias("itemLicitacao.licitacao", "l");
		
				if(chave != null){
					criteria.add(Restrictions.eq("l.id", chave));
				}
				
				if(StringUtils.isNotBlank(nome)){
					criteria.add(Restrictions.ilike("p.nome", "%"+nome+"%"));
				}
				
				return criteria.addOrder(Order.asc("p.nome")).list();
		
				
	}
	
}
