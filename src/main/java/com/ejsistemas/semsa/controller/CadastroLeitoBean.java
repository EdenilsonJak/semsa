package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.model.Medico;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.repository.MedicoRepository;
import com.ejsistemas.semsa.repository.filter.ClinicaFilter;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.repository.filter.LeitoFilter;
import com.ejsistemas.semsa.service.CadastroLeitoService;
import com.ejsistemas.semsa.service.NegocioException;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroLeitoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	CadastroLeitoService cadastroLeitoService;
	
	@Inject
	FornecedorRepository fornecedorRepository;
	
	@Inject
	MedicoRepository medicoRepository;
	
	@Inject
	ClinicaRepository clinicaRepository;
	
	@Inject
	LeitoRepository leitoRepository;
	
	private Leito leito;

	private List<Fornecedor> fornecedores = new ArrayList<>();
	private List<Clinica> clinicas = new ArrayList<>();
	private List<Medico> medicos = new ArrayList<>();
	private List<Leito> leitos = new ArrayList<>();
	
	public CadastroLeitoBean() {
		limpar();
	}
	
	
	public void inicializar(){
			this.fornecedores = fornecedorRepository.hospitalFiltrados(new FornecedorFilter());
			this.clinicas = clinicaRepository.filtrados(new ClinicaFilter());
			/*this.medicos = medicoRepository.buscarTodosMedicos();*/
	}
	
	public void salvar(){
		if(leito.isNovo()){
			if(existeLeito(leito)){
				FacesUtil.addAlerMessage("Leito já encontra-se cadastro no sistema!");
			}
			else{
				this.leito.setDataCriacao(new Date());
				this.leito.setStatus("Ausente");
				this.leito = cadastroLeitoService.salvar(leito);
				FacesUtil.addInfoMessage("Leito cadastrado com sucesso!");
				limpar();
				
			}
		}else{
			this.leito = cadastroLeitoService.salvar(leito);
			FacesUtil.addInfoMessage("Leito atualizado com sucesso!");
			limpar();
		}
	}
	
	private boolean existeLeito(Leito leito2) {
		boolean existeLeito = false;
		this.leitos = leitoRepository.filtrados(new LeitoFilter());
		
		for(Leito leito : leitos){
			if(leito2.getNome().equals(leito.getNome()) &&
					leito.getClinica() == leito2.getClinica() && 
					leito.getHospital() == leito2.getHospital()){
				existeLeito = true;
				break;
			}
		}
		return existeLeito;	
	}
	
	public void limpar(){
		leito = new Leito();
		/*fornecedores = new ArrayList<>();
		clinicas = new ArrayList<>();
		medicos = new ArrayList<>();
		leitos = new ArrayList<>();*/
		
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}


	public List<Clinica> getClinicas() {
		return clinicas;
	}

	public List<Medico> getMedicos() throws NegocioException {		
		try {
			if((medicos == null) || (medicos.size() ==0)){
			medicos = medicoRepository.buscarTodosMedicos();
		}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		return medicos;
	}


	public List<Leito> getLeitos() {
		return leitos;
	}
	
	
	
	
}
