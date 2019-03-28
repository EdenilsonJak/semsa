package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Procedimento;
import com.ejsistemas.semsa.repository.ProcedimentoRepository;

public class CadastroProcedimentoService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProcedimentoRepository procedimentoRepository;
	
	public Procedimento buscarPorId(Long id){
		return procedimentoRepository.porId(id);
	}
	
	public List<Procedimento> listarTodos(){
		return procedimentoRepository.listarTodos();
	}
	
	public List<Procedimento> listarPorCodigoNome(String valor){
		return procedimentoRepository.porValores(valor);
	}
	
}
