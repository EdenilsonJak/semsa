package com.ejsistemas.semsa.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.service.CadastroFornecedorService;
import com.ejsistemas.semsa.service.WebServiceCep;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CadastroFornecedorService cadastroFornecedorService;

	@Inject
	private FornecedorRepository fornecedorRepository;

	private List<Fornecedor> fornecedores;

	private Fornecedor fornecedor;
	private Fornecedor fornecedorEdicao;
	private WebServiceCep webServiceCep;
	private int activeIndex;

	public void inicializar() {
		this.fornecedores = this.fornecedorRepository.filtrados(new FornecedorFilter());
	}

	public CadastroFornecedorBean() {
		limpar();
	}

	public void salvar() {
		if (fornecedor.isNovo()) {
			if (existeCnpj(fornecedor)) {
				FacesUtil.addAlerMessage("CNPJ para este Fornecedor já cadastrado!");
			} else {
				this.fornecedor.setDataCriacao(new Date());
				this.fornecedor = cadastroFornecedorService.salvar(fornecedor);
				limpar();
				FacesUtil.addInfoMessage("Fornecedor cadastrado com sucesso!");

			}
		} else {
			this.fornecedor = cadastroFornecedorService.salvar(fornecedor);
			limpar();
			FacesUtil.addInfoMessage("Fornecedor atualizado com sucesso!");
		}
	}

	public void salvarHospital() {
		if (fornecedor.isNovo()) {
			if (existeCnpj(fornecedor)) {
				FacesUtil.addAlerMessage("CNPJ para este Hospital já cadastrado!");
			} else {
				this.fornecedor.setDataCriacao(new Date());
				this.fornecedor.setStatus("hospital");
				this.fornecedor = cadastroFornecedorService.salvarHospital(fornecedor);
				this.limpar();
				FacesUtil.addInfoMessage("Hospital cadastrado com sucesso!");

			}
		} else {
			this.fornecedor = cadastroFornecedorService.salvar(fornecedor);
			limpar();
			FacesUtil.addInfoMessage("Hospital atualizado com sucesso!");
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	private void limpar() {
		fornecedor = new Fornecedor();
		fornecedorEdicao = new Fornecedor();
		//this.fornecedores = new ArrayList<>();
	}
	
	public void receberFornecedor(Fornecedor fornecedor, int linha){
		setFornecedor(fornecedor);
		RequestContext context = RequestContext.getCurrentInstance();
		goToTab(0);
		context.update("tabPanelWidget");
		
	}

	public boolean isEditando() {
		return this.fornecedor.getId() != null;
	}

	public void consultaCep() {
		webServiceCep = WebServiceCep.searchCep(this.fornecedor.getCep());

		if (webServiceCep.wasSuccessful()) {
			this.fornecedor.setTipoLogradouro(webServiceCep.getLogradouroType());
			this.fornecedor.setLogradouro(webServiceCep.getLogradouro());
			this.fornecedor.setBairro(webServiceCep.getBairro());
			this.fornecedor.setCidade(webServiceCep.getCidade());
			this.fornecedor.setEstado(webServiceCep.getUf());
		} else {
			FacesUtil.addErrorMessage("Cep Incorreto!");
		}

	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public Fornecedor getFornecedorEdicao() {
		return fornecedorEdicao;
	}

	public void setFornecedorEdicao(Fornecedor fornecedorEdicao) {
		this.fornecedorEdicao = fornecedorEdicao;
	}

	public void onSelect() {
		if(this.fornecedor != null && this.fornecedorEdicao != null){
			this.fornecedor = new Fornecedor();
			this.fornecedor = this.fornecedorEdicao;
			this.fornecedorEdicao = new Fornecedor();
		}
	}

	private boolean existeCnpj(Fornecedor fornecedor) {
		boolean existeCNPJ = false;
		this.fornecedores = fornecedorRepository.filtrados(new FornecedorFilter());

		for (Fornecedor fornConsulta : this.fornecedores) {
			if (fornConsulta.getCnpj().equals(fornecedor.getCnpj())) {
				existeCNPJ = true;
				break;
			}
		}

		return existeCNPJ;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	public void goToTab(int index){
		this.activeIndex = index;
	}
}
