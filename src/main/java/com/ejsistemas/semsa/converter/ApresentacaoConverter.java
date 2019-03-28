package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Apresentacao;
import com.ejsistemas.semsa.repository.ApresentacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Apresentacao.class)
public class ApresentacaoConverter implements Converter {
	
	//@Inject
	private ApresentacaoRepository apresentacaoRepository;

	public ApresentacaoConverter() {
		apresentacaoRepository = CDIServiceLocator.getBean(ApresentacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Apresentacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = apresentacaoRepository.porId(id);
		}
		return retorno;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			return ((Apresentacao) value).getId().toString();
		}
		return "";
	}

}
