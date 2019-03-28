package com.ejsistemas.semsa.controller.licitacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.model.StatusPedido;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.repository.filter.LicitacaoFilter;

@Named
@ViewScoped
public class PesquisaLicitacaoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LicitacaoRepository licitacaoRepository;
	
	private LicitacaoFilter filtro;
	private List<Licitacao> licitacoesFiltrados;
	
	public PesquisaLicitacaoBean(){
		filtro = new LicitacaoFilter();
		licitacoesFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		licitacoesFiltrados = this.licitacaoRepository.filtrados(filtro);
	}
	
	
	public List<Licitacao> getLicitacoesFiltrados() {
		return licitacoesFiltrados;
	}

	public LicitacaoFilter getFiltro() {
		return filtro;
	}
	
	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}
	

}
