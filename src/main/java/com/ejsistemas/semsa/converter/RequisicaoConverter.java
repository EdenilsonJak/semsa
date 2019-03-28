package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Requisicao;
import com.ejsistemas.semsa.repository.RequisicaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Requisicao.class)
public class RequisicaoConverter implements Converter {
	
	private RequisicaoRepository requisicaoRepository;
	
	public RequisicaoConverter(){
		
		requisicaoRepository = CDIServiceLocator.getBean(RequisicaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Requisicao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = this.requisicaoRepository.porId(id);
		}
		
		return retorno;
	}	


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Requisicao requisicao = (Requisicao) value;
 			return requisicao.getId() == null ? null : requisicao.getId().toString();
		}
		return "";
	}

}
