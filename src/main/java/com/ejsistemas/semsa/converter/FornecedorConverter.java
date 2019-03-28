package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Fornecedor;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {
/*
	private FornecedorRepository fornecedorRepository;
	
	public FornecedorConverter() {
		fornecedorRepository = CDIServiceLocator.getBean(FornecedorRepository.class);
	}*/

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
			Fornecedor fornecedor = (Fornecedor) value;
			this.addAttribute(component, fornecedor);
			return fornecedor.getId() == null ? null: fornecedor.getId().toString();
		}
		return "";
	}
	
	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, Fornecedor fornecedor){
		this.getAttributesFrom(component).put(fornecedor.getId().toString(), fornecedor);
	}

}
