package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;

public class CadastroLeitoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	LeitoRepository leitoRepository;
	
	private Leito leito;
	
	
	@Transactional
	public Leito salvar(Leito leito){
		return this.leito = leitoRepository.guardar(leito);
	}
	
	public Leito getLeito() {
		return leito;
	}
	
	
	
	
}
