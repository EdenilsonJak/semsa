package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.dvs.AtividadeEconomica;

@FacesConverter(value = "atividadeConverter")
public class AtividadeConverter implements Converter {
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
			AtividadeEconomica economica = (AtividadeEconomica) value;
			this.addAttribute(component, economica);
			return economica.getCdatividadeeconomica() == null ? null : economica.getCdatividadeeconomica().toString();
		}
		return "";
	}
	
	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, AtividadeEconomica economica){
		this.getAttributesFrom(component).put(economica.getCdatividadeeconomica().toString(), economica);
	}
	
}
