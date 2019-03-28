package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.model.UnidadeOrcamentaria;
import com.ejsistemas.semsa.repository.UnidadeOrcamentariaRepository;

@Named
@ApplicationScoped
public class SelecaoUnidadeOrcamentaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeOrcamentariaRepository orcamentariaRepository;

	private List<UnidadeOrcamentaria> unidadeOrcamentariasFiltrados;
	
	@Inject
	private UnidadeOrcamentaria unidadeOrcamentaria;

	private String nome;

	public void inicializar() {
		abrirDialogo();
		
		
	}

	public void pesquisar() {
		unidadeOrcamentariasFiltrados = orcamentariaRepository.porNome(nome);
	}

	public void selecionar(UnidadeOrcamentaria unidade){
		this.setUnidadeOrcamentaria(unidade);
		RequestContext.getCurrentInstance().closeDialog(unidade);
	}
	
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		// opcoes.put("responsive", true);
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("width", 630);
		opcoes.put("height", 400);

		RequestContext.getCurrentInstance().openDialog("/dialogs/UnidadeOrcamentaria", opcoes, null);
	}

	public List<UnidadeOrcamentaria> getUnidadeOrcamentariasFiltrados() {
		return unidadeOrcamentariasFiltrados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}
	
	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}
}
