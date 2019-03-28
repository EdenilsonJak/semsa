package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class EmissaoRequisicaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroRequisicaoService cadastroRequisicaoService;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;
	
	@Inject
	private EstoqueRequisicaoService estoqueRequisicaoService;
	
	@Transactional
	public Requisicao emitir(Requisicao requisicao){
		requisicao = this.cadastroRequisicaoService.salvar(requisicao);
		
		if(requisicao.isNaoEmissivel()){
			throw new NegocioException("Requisicao não pode ser emitido com status "
					+ requisicao.getStatus().getDescricao()+ ".");
		}
		
		this.estoqueRequisicaoService.baixarItensEstoque(requisicao);
		
		requisicao.setDataEmissao(new Date());
		requisicao.setStatus(StatusPedido.EMITIDO);
		
		requisicao = this.requisicaoRepository.guardar(requisicao);
		
		return requisicao;
	}

}
