package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Receituario;
import com.ejsistemas.semsa.repository.RecepcaoReceituarioRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroReceituarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	RecepcaoReceituarioRepository receituarioRepository;
	
	private Receituario receituario;
	
	@Transactional
	public Receituario salvar(Receituario receituario){
		if(receituario.getItens().isEmpty()){
			throw new NegocioException("Insira pelo menos uma requisição!");
		}
		return receituarioRepository.guardar(receituario);
	}	
	
	public Receituario getReceituario() {
		return receituario;
	}
}
