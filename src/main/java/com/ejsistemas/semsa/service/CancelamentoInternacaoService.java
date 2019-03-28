package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CancelamentoInternacaoService implements Serializable {

	/**
	 * Desenvolvido por Edenilson Mendonça proprietário ejsistemas
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private InternacaoRepository internacaoRepository;
	
	@Transactional
	public Internacao cancelar(Internacao internacao) {
		
		if(internacao.isOcupado()){
			internacao.setStatus("CANCELADO");
			internacao = this.internacaoRepository.guardar(internacao);
		}else {
			throw new NegocioException("Internação não pode ser Cancelado com o status: "
					+ internacao.getStatus() + ".");
		}
		
		
		return internacao;
	}

}
