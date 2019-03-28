package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Leito;
import com.ejsistemas.semsa.repository.LeitoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Leito.class)
public class LeitoConverter implements Converter{
	
	private LeitoRepository LeitoRepository;
	
	public LeitoConverter() {
		LeitoRepository = CDIServiceLocator.getBean(LeitoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Leito retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = LeitoRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Leito leito = (Leito) value;
			return leito.getId() == null ? null : leito.getId().toString();
		}
		return "";
	}

}
