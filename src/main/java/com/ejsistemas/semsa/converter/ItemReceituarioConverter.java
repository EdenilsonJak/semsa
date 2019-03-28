package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.ItemReceituario;
import com.ejsistemas.semsa.repository.ItemReceituarioRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ItemReceituario.class)
public class ItemReceituarioConverter implements Converter {

	private ItemReceituarioRepository itemReceituarioRepository;
	
	public ItemReceituarioConverter() {
		itemReceituarioRepository = CDIServiceLocator.getBean(ItemReceituarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ItemReceituario retorno = null;
		if(value != null){
			Long id = new Long(value);
			retorno = itemReceituarioRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((ItemReceituario) value).getId().toString();
		}
		
		return "";
	}

}
