package com.ejsistemas.semsa.controller.recepcao;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.MedicoRepository;

@Named
@ViewScoped
public class SelecaoMedicoBean implements Serializable{

	/**
	 * Desenvolvido por Edenilson Mendonça
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MedicoRepository medicoRepository;
	
	private String nome;
	
	private List<Medico> medicosFiltrados;

	public void pesquisar(){
		medicosFiltrados = medicoRepository.porValores(this.nome);
	}
	
	public void abrirDialogo(){
		RequestContext.getCurrentInstance().openDialog("/recepcao/registros/SelecaoMedicos");
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Medico> getMedicosFiltrados() {
		return medicosFiltrados;
	}
	
	
	
}
