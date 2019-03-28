package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroRequisicaoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;
	
	@Transactional
	public Requisicao salvar(Requisicao requisicao){
		if(requisicao.isNovo()){
			requisicao.setDataRequisicao(new Date());
			requisicao.setStatus(StatusPedido.INICIAL);
		}
		return requisicaoRepository.guardar(requisicao);
	}

}
