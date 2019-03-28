package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Paciente;
import com.ejsistemas.semsa.repository.filter.PacienteFilter;
import com.ejsistemas.semsa.service.CadastroPacienteService;


@Named
@ViewScoped
public class PesquisaPacienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroPacienteService cadastroPacienteService;
	
	private PacienteFilter pacienteFilter;
	private List<Paciente> pacientes;
	
	
	public PesquisaPacienteBean() {
		pacienteFilter = new PacienteFilter();
		
	}
	
	public void pesquisar(){
		this.pacientes = cadastroPacienteService.buscarFiltrados(pacienteFilter);
				}
	
	public PacienteFilter getPacienteFilter() {
		return pacienteFilter;
	}

	public void setPacienteFilter(PacienteFilter pacienteFilter) {
		this.pacienteFilter = pacienteFilter;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	
}
