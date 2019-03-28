package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CancelamentoRequisicaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;
	
	@Inject
	private EstoqueRequisicaoService estoqueRequisicaoService;
	
	@Transactional 
	public Requisicao cancelar(Requisicao requisicao){
		
		//String obsCancelamento = requisicao.getObsCancelamento();
		//requisicao = this.requisicaoRepository.porId(requisicao.getId());
		
		if(requisicao.isNaoCancelavel()){
			throw new NegocioException("Requisição não pode ser cancelado no status "
					+ requisicao.getStatus().getDescricao() + ".");
		}
		
		if(requisicao.isEmitido()){
			this.estoqueRequisicaoService.retornarItensRequisicaoEstoque(requisicao);
		}
		
		requisicao.setStatus(StatusPedido.CANCELADO);
		//requisicao.setObsCancelamento(obsCancelamento);
		
		requisicao = this.requisicaoRepository.guardar(requisicao);
		
		return requisicao;
		
	}

}
