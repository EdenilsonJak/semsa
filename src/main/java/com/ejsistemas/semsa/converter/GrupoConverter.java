package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Grupo;
import com.ejsistemas.semsa.repository.GrupoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Grupo.class)
public class GrupoConverter implements Converter{

	private GrupoRepository grupoRepository;
	
	public GrupoConverter(){
		this.grupoRepository = (GrupoRepository) CDIServiceLocator.getBean(GrupoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;

		if (value != null) {
			retorno = this.grupoRepository.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo.getId() == null ? null : grupo.getId().toString();
			
		}
		return "";
	}

}
