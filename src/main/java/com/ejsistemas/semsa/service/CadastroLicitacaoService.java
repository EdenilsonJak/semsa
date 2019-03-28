package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroLicitacaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LicitacaoRepository licitacaoRepository;
	
	@Transactional
	public Licitacao salvar(Licitacao licitacao){
		if(licitacao.isNovo()){
			licitacao.setDataLicitacao(new Date());
			licitacao.setStatus(StatusPedido.INICIAL);
		}
		licitacao.recalcularValorGlobal();
		
		if(licitacao.isNaoAlteravel()){
			throw new NegocioException("Licitação não pode ser alterado no status " +
					licitacao.getStatus().getDescricao() + ".");
		}
		
		if(licitacao.getItens().isEmpty()){
			throw new NegocioException("Licitacao deve possuir pelo menos um item.");
		}
		
		if(licitacao.isValorTotalNegativo()){
			throw new NegocioException("Valor Total da Licitação não pode ser negativo!");
		}
		
		licitacao  = this.licitacaoRepository.guardar(licitacao);
		return licitacao;
	}

}
