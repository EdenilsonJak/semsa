package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Categoria;
import com.ejsistemas.semsa.repository.CategoriaRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	//@Inject
	private CategoriaRepository categoriaRepository;
	
	public CategoriaConverter(){
		categoriaRepository = CDIServiceLocator.getBean(CategoriaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = categoriaRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			return ((Categoria) value).getId().toString();
		}
		return "";
	}

}
