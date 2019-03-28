package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Clinica;
import com.ejsistemas.semsa.repository.ClinicaRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Clinica.class)
public class ClinicaConverter implements Converter {

	//@Inject
	private ClinicaRepository ClinicaRepository;
	
	public ClinicaConverter(){
		ClinicaRepository = CDIServiceLocator.getBean(ClinicaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Clinica retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = ClinicaRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Clinica clinica = (Clinica) value;
			return clinica.getId() == null ? null : clinica.getId().toString();
		}
		return "";
	}

}
