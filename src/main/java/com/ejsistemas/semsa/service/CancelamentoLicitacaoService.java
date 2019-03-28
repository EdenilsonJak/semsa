package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CancelamentoLicitacaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	LicitacaoRepository licitacaoRepository;

	@Transactional
	public Licitacao cancelar(Licitacao licitacao) {
		licitacao = this.licitacaoRepository.porId(licitacao.getId());
		
		if(licitacao.isNaoCancelavel()){
			throw new NegocioException("Contrato não pode ser cancelada no status "
					+ licitacao.getStatus().getDescricao() + ".");
		}
		
		licitacao.setStatus(StatusPedido.CANCELADO);
		
		licitacao = this.licitacaoRepository.guardar(licitacao);
		
		return licitacao;
	}

}
