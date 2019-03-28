package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.repository.filter.LeitoFilter;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaLeitoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	LeitoRepository leitoRepository;
	
	@Inject
	FornecedorRepository fornecedorRepository;
	
	@Inject
	ClinicaRepository clinicaRepository;
	
	private LeitoFilter leitoFilter;
	private List<Leito> leitos;
	private List<Fornecedor> fornecedors;
	private List<Clinica> clinicas;
	
	
	public PesquisaLeitoBean() {
		leitoFilter = new LeitoFilter();
	}
	
	public void pesquisar(){
		this.leitos = leitoRepository.filtrados(leitoFilter);
	}
	
	public LeitoFilter getLeitoFilter() {
		return leitoFilter;
	}
	public List<Leito> getLeitos() {
		return leitos;
	}

	public List<Fornecedor> getFornecedors() throws NegocioException{
		try {
			if((fornecedors == null) || (fornecedors.size() ==0)){
				fornecedors = fornecedorRepository.hospitalFiltrados(new FornecedorFilter());
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		return fornecedors;
	}

	public List<Clinica> getClinicas() throws NegocioException {
		try {
			if((clinicas == null)||(clinicas.size() ==0)){
				clinicas = clinicaRepository.filtrados(new ClinicaFilter());
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		return clinicas;
	}
	
	
	
}
