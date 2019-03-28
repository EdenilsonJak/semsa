package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.filter.MedicoFilter;
import com.ejsistemas.semsa.service.CadastroMedicoService;


@Named
@ViewScoped
public class PesquisaMedicoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroMedicoService cadastroMedicoService;
	
	private MedicoFilter medicoFilter;
	private List<Medico> medicos;
	
	
	public PesquisaMedicoBean() {
		medicoFilter = new MedicoFilter();
		
	}
	
	public void pesquisar(){
		this.medicos = cadastroMedicoService.buscarTodos(medicoFilter);
	}
	
	
	public MedicoFilter getMedicoFilter() {
		return medicoFilter;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}

}
