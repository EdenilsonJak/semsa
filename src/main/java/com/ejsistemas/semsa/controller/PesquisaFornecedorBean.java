package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;

@Named
@ViewScoped
public class PesquisaFornecedorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	FornecedorRepository fornecedorRepository;
	
	private FornecedorFilter fornecedorFilter;
	private List<Fornecedor> fornecedoresFiltrados;
	
	public PesquisaFornecedorBean(){
		fornecedorFilter = new FornecedorFilter();
	}

	public void pesquisar(){
		fornecedoresFiltrados = fornecedorRepository.filtrados(fornecedorFilter);
	}
	
	public void pesquisarHospitais(){
		fornecedoresFiltrados = fornecedorRepository.hospitalFiltrados(fornecedorFilter);
	}
	
	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}
	
	public List<Fornecedor> porNomeForcedorFantasia(String campos){
		return fornecedorRepository.porFornecedorFantasia(campos);
	}

	public FornecedorFilter getFornecedorFilter() {
		return fornecedorFilter;
	}

}
