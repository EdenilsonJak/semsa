package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Grupo_usuario;
import com.ejsistemas.semsa.repository.Grupo_usuarioRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Grupo_usuario.class)
public class GrupoUsuarioConverter implements Converter {

	private Grupo_usuarioRepository grupo_usuarioRepository;
	
	public GrupoUsuarioConverter(){
		this.grupo_usuarioRepository = (Grupo_usuarioRepository) CDIServiceLocator.getBean(Grupo_usuarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo_usuario retorno = null;

		if (value != null) {
			retorno = this.grupo_usuarioRepository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo_usuario grupo_usuario = (Grupo_usuario) value;
			return grupo_usuario.getId() == null ? null : grupo_usuario.getId().toString();
		}
		return "";
	}

}
