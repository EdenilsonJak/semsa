package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.TipoLicitacao;
import com.ejsistemas.semsa.repository.TipoLicitacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TipoLicitacao.class)
public class TipoLicitacaoConverter implements Converter{
	
	//@Inject
	private TipoLicitacaoRepository licitacaoRepository;
	
	public TipoLicitacaoConverter(){
		licitacaoRepository = (TipoLicitacaoRepository) CDIServiceLocator.getBean(TipoLicitacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoLicitacao retorno = null;
		
		if(value != null){
			retorno = this.licitacaoRepository.porId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((TipoLicitacao) value).getId().toString();
		}
		
		return "";
	}

}
