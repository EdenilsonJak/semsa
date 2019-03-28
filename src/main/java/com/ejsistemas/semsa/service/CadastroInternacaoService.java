package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.model.vo.MesAnoVO;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroInternacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	InternacaoRepository internacaoRepository;
	
	private Internacao internacao;

	@Transactional
	public Internacao salvar(Internacao internacao){
		if(internacao.getObs_interncao() != null){
			internacao.setObs_interncao(internacao.getObs_interncao().toUpperCase());
		}
		if(internacao.getObs_alta() != null){
			internacao.setObs_alta(internacao.getObs_alta().toUpperCase());
		}
		return this.internacao = internacaoRepository.guardar(internacao);
	}
	
	public Internacao getInternacao() {
		return internacao;
	}
}
