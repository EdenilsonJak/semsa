package com.ejsistemas.semsa.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Fornecedor;
import com.ejsistemas.semsa.repository.FornecedorRepository;
import com.ejsistemas.semsa.repository.filter.FornecedorFilter;
import com.ejsistemas.semsa.util.jpa.Transactional;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class CadastroFornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FornecedorRepository fornecedorRepository;

	private List<Fornecedor> fornecedores;

	private Fornecedor fornecedor;

	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		return this.fornecedor = fornecedorRepository.guardar(fornecedor);

	}

	@Transactional
	public Fornecedor salvarHospital(Fornecedor fornecedor) {
		return this.fornecedor = fornecedorRepository.guardarHospital(fornecedor);
	}
	
	public List<Fornecedor> filtroFornecedor(FornecedorFilter filter){
		return fornecedorRepository.hospitalFiltrados(filter);
	}
	
	public List<Fornecedor> porNomeFornecedorFantasia(String campos){
		return fornecedorRepository.porFornecedorFantasia(campos);
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

}
