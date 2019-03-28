package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.MedicoRepository;
import com.ejsistemas.semsa.repository.filter.MedicoFilter;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroMedicoService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	MedicoRepository medicoRepository;
	
	private Medico medico;
	
	private List<Medico> medicos;

	@Transactional
	public Medico salvar(Medico medico){
		return this.medico = medicoRepository.guardar(medico);		
	}

	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public List<Medico> buscarTodos(MedicoFilter filtro){
		return medicoRepository.filtrados(filtro);
	}
	
	public List<Medico> buscarPorValores(String valores){
		return medicoRepository.porValores(valores);
	}
}
