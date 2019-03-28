package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Solicitacao;
import com.ejsistemas.semsa.repository.SolicitaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Solicitacao.class)
public class SolicitacaoConverter implements Converter<Object>{

	private SolicitaoRepository solicitacaoRepository;
	
	public SolicitacaoConverter() {
		solicitacaoRepository = CDIServiceLocator.getBean(SolicitaoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Solicitacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = solicitacaoRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		if(value != null){
			Solicitacao solicitacao = (Solicitacao) value;
			return solicitacao.getId() == null ? null : solicitacao.getId().toString();
		}
		return "";
	}

}
