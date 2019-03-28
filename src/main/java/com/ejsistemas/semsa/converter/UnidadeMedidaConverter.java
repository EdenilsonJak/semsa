package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.UnidadeMedida;
import com.ejsistemas.semsa.repository.UnidadeMedidaRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = UnidadeMedida.class)
public class UnidadeMedidaConverter implements Converter{
	
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public UnidadeMedidaConverter(){
		this.unidadeMedidaRepository = CDIServiceLocator.getBean(UnidadeMedidaRepository.class );
	}

	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		UnidadeMedida retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = this.unidadeMedidaRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			UnidadeMedida unidadeMedida = (UnidadeMedida) value;
			return unidadeMedida.getId() == null ? null : unidadeMedida.getId().toString();
		}
		return "";
	}

}
