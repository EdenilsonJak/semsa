package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Medico;

@FacesConverter(value = "medicoConverter")
public class MedicoConverter implements Converter {
/*
	private MedicoRepository medicoRepository;
	
	public MedicoConverter() {
		medicoRepository = CDIServiceLocator.getBean(MedicoRepository.class);
	}*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		/*Medico retorno = null;*/
		
		if(value != null){
		/*	Long id = new Long(value);*/
			/*retorno = medicoRepository.porId(id);*/
			return this.getAttributesFrom(component).get(value); 
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		if(value != null){
			Medico medico = (Medico) value;
			this.addAttribute(component, medico);
			return medico.getId() == null ? null : medico.getId().toString();
		}
		return "";
	}
	
	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, Medico medico){
		this.getAttributesFrom(component).put(medico.getId().toString(), medico);
	}
	
}
