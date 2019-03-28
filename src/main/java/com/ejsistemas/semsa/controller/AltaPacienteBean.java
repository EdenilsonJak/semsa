package com.ejsistemas.semsa.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.qualificador.edicao.InternaEdicao;

@Named
@ViewScoped
public class AltaPacienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@InternaEdicao
	private Internacao internacao;
	
	

}
