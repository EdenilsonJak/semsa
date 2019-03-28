package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Internacao;
import com.ejsistemas.semsa.repository.InternacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Internacao.class)
public class InternacaoConverter implements Converter {

	private InternacaoRepository InternacaoRepository;
	
	public InternacaoConverter() {
		InternacaoRepository = CDIServiceLocator.getBean(InternacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Internacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = InternacaoRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		if(value != null){
			Internacao internacao = (Internacao) value;
			return internacao.getId() == null ? null : internacao.getId().toString();
		}
		return "";
	}
	
}
