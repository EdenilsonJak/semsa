package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Pericia;
import com.ejsistemas.semsa.repository.PericiaRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Pericia.class)
public class PericiaConverter implements Converter {

	private PericiaRepository periciaRepository;
	
	public PericiaConverter() {
		periciaRepository = CDIServiceLocator.getBean(PericiaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pericia retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = periciaRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		if(value != null){
			Pericia pericia = (Pericia) value;
			return pericia.getId_pericia() == null ? null : pericia.getId_pericia().toString();
		}
		return "";
	}
	
}
