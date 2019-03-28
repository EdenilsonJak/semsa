package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.service.CadastroClinicaService;

@Named
@ViewScoped
public class CadastroClinicaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroClinicaService cadastroClinicaService;
	
	private List<Clinica> clinicas;
	
	private Clinica clinica;
	
		
	public CadastroClinicaBean() {
		limpar();
	}
	
	public void inicializar(){
		this.clinicas = cadastroClinicaService.buscarTodos(new ClinicaFilter());
	}

	public void limpar(){
		this.clinica = new Clinica();
		this.clinicas = new ArrayList<>();
	}
	
	public void salvar(){
		this.clinica.setDataCriacao(new Date());
		this.clinica = cadastroClinicaService.salvar(clinica);
		this.limpar();
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}
	
}
