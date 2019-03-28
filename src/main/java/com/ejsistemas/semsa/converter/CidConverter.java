package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Cid10;

@FacesConverter(value = "cidConverter")
public class CidConverter implements Converter{

	/*private CidRepository cidRepository;*/
	
	/*public CidConverter() {
		cidRepository = CDIServiceLocator.getBean(CidRepository.class);
	}*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		/*Cid10 retorno = null;*/
		if(value != null){
			return getAttributesFrom(component).get(value);
			/*Long id = new Long(value);
			retorno = cidRepository.porId(id);*/
		}	
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Cid10 cid = (Cid10) value;
			this.addAttribute(component, cid);
			return cid.getId() == null ? null : cid.getId().toString();
		}	
		return "";
	}
	
	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, Cid10 cid){
		this.getAttributesFrom(component).put(cid.getId().toString(), cid);
	}

}
