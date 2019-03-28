package com.ejsistemas.semsa.controller.requisicao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.repository.filter.RequisicaoFilter;

@Named
@ViewScoped
public class PesquisaRequisicaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RequisicaoRepository requisicaoRepository;
	
	private RequisicaoFilter requisicaoFilter;
	
	private List<Requisicao> requisicoesFiltrados;
	
	public PesquisaRequisicaoBean(){
		requisicaoFilter = new RequisicaoFilter();
		requisicoesFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		requisicoesFiltrados = requisicaoRepository.filtrados(requisicaoFilter);
	}

	public RequisicaoFilter getRequisicaoFilter() {
		return requisicaoFilter;
	}

	public List<Requisicao> getRequisicoesFiltrados() {
		return requisicoesFiltrados;
	}
	
	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}
	

}
