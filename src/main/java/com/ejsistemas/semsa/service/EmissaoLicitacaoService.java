package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class EmissaoLicitacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LicitacaoRepository licitacaoRepository;
	
	
	@Transactional
	public Licitacao emitir(Licitacao licitacao){
		
		//licitacao = this.cadastroLicitacaoService.salvar(licitacao);		
		
		if(licitacao.isNaoEmissivel()){
			throw new NegocioException("Contrato não pode ser emitida com status " 
					+ licitacao.getStatus().getDescricao() + ".");
		}
		
		if(licitacao.getValor_global().compareTo(BigDecimal.ZERO) == 0){
			throw new NegocioException("Contrato não pode ser emitido com Total: R$0.00");
		}
		
		//this.estoqueItemService.registrarItensEstoque(licitacao);
		
		licitacao.setStatus(StatusPedido.EMITIDO);
		licitacao.setDt_emissao(new Date());
		
		licitacao = this.licitacaoRepository.guardar(licitacao);
		
		return licitacao;
	}

}
