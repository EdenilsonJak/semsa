package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.util.jpa.Transactional;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

public class CadastroClinicaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	ClinicaRepository clinicaRepository;
	
	private Clinica clinica;
	
	private List<Clinica> clinicas;
	
	@Transactional
	public Clinica salvar(Clinica clinica){
		if(clinica.isNovo()){
				if(existeClinica(clinica)){
					FacesUtil.addErrorMessage("Clinica já cadastrado!");
				}
				else{
					this.clinica = clinicaRepository.guardar(clinica);
					FacesUtil.addInfoMessage("Clínica cadastrada com sucesso!");

			}
		}else{
			this.clinica = clinicaRepository.guardar(clinica);
			FacesUtil.addInfoMessage("Clínica atualizado com sucesso!");
		}
		return this.clinica;
		
	}

	private boolean existeClinica(Clinica clinica) {
		boolean existeClinica = false;
		this.clinicas = clinicaRepository.filtrados(new ClinicaFilter());
		
		for(Clinica clinicaConsulta : this.clinicas){
			if(clinicaConsulta.getNome().equals(clinica.getNome())){
				existeClinica = true;
				break;
			}
		}
		
		return existeClinica;
	}

	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	public List<Clinica> buscarTodos(ClinicaFilter filtro){
		return clinicaRepository.filtrados(filtro);
	}
}
