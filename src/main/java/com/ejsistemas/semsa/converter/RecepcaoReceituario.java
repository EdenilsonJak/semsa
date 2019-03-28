package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Receituario;
import com.ejsistemas.semsa.repository.RecepcaoReceituarioRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Receituario.class)
public class RecepcaoReceituario implements Converter{

	private RecepcaoReceituarioRepository receituarioRepository;
	
	public RecepcaoReceituario(){
		receituarioRepository = CDIServiceLocator.getBean(RecepcaoReceituarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Receituario retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = receituarioRepository.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Receituario) value).getId().toString();
		}
		return "";
	}

}
