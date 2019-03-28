package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Procedimento;

@FacesConverter(value = "procedimentoConverter")
public class ProcedimentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value != null){
			return this.getAttributesFrom(component).get(value);
		}
		return null;	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Procedimento procedimento = (Procedimento) value;
			this.addAttribute(component, procedimento);
			return procedimento.getId() == null ? null : procedimento.getId().toString();
		}
		return "";
	}

	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, Procedimento procedimento){
		this.getAttributesFrom(component).put(procedimento.getId().toString(), procedimento);
	}
}
