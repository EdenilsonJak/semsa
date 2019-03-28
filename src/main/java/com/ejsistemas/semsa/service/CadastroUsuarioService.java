package com.ejsistemas.semsa.service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.ejsistemas.semsa.model.Usuario;
import com.ejsistemas.semsa.repository.UsuarioRepository;
import com.ejsistemas.semsa.util.jpa.Transactional;
import com.ejsistemas.semsa.util.jsf.FacesUtil;

public class CadastroUsuarioService implements Serializable {

	/**
	 * Cadastro de usuarios serviço
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
			return usuarioRepository.guardar(usuario);
	}

}
