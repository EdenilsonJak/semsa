package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Licitacao;
import com.ejsistemas.semsa.repository.LicitacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Licitacao.class)
public class LicitacaoConverter implements Converter{
	
	//@Inject
	private LicitacaoRepository licitacaoRepository;
	
	public LicitacaoConverter(){
		licitacaoRepository = CDIServiceLocator.getBean(LicitacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Licitacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = licitacaoRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Licitacao licitacao = (Licitacao) value;
 			return licitacao.getId() == null ? null : licitacao.getId().toString();
		}
		return "";
	}

}
