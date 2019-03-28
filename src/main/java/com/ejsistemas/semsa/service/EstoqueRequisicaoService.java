package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.ItemRequisicao;
import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class EstoqueRequisicaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;
	
	@Transactional
	public void baixarItensEstoque(Requisicao requisicao){
		requisicao = this.requisicaoRepository.porId(requisicao.getId());
		
		for(ItemRequisicao item : requisicao.getItens()){
			item.getItemLicitacao().baixarEstoque(item.getQuantidadeItem());
		}
		
	}

	@Transactional
	public void retornarItensRequisicaoEstoque(Requisicao requisicao) {
		
		requisicao = requisicaoRepository.porId(requisicao.getId());
		
		for(ItemRequisicao item : requisicao.getItens()){
			item.getItemLicitacao().adicionarEstoque(item.getQuantidadeItem());
		}
	}

}
