package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Grupo;
import com.ejsistemas.semsa.model.UnidadeSaude;
import com.ejsistemas.semsa.repository.UnidadeSaudeRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=UnidadeSaude.class)
public class UnidadeSaudeConverter implements Converter{

	private UnidadeSaudeRepository unidadeSaudeRepository;
	
	public UnidadeSaudeConverter() {
		unidadeSaudeRepository = CDIServiceLocator.getBean(UnidadeSaudeRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UnidadeSaude retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = unidadeSaudeRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			UnidadeSaude unidadeSaude = (UnidadeSaude) value;
			return unidadeSaude.getId() == null ? null : unidadeSaude.getId().toString();
		}
		
		return "";
	}

}
