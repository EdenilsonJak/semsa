package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class TransferenciaPacienteService implements Serializable {

	/**
	 * Serviço de transferencia que sera injetada na classe TransferenciaPacienteBean
	 * Edenilson mendonça dos santos
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private InternacaoRepository internacaoRepository;
	
	@Transactional
	public Internacao transferencia(Internacao internacao){
		if(internacao.isTransferido()){
			//internacao.setStatus("OCUPADO");
			internacao = this.internacaoRepository.guardar(internacao);
		}else{
			throw new NegocioException("Paciente não pode ser transferido com status: "
					+ internacao.getStatus() + ".");
		}
		return internacao;
	}
}
