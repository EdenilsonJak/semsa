package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Publicacao;
import com.ejsistemas.semsa.repository.PublicacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Publicacao.class)
public class PublicacaoConverter implements Converter {
	
	private PublicacaoRepository publicacoes;
	
	public PublicacaoConverter(){
		this.publicacoes = (PublicacaoRepository) CDIServiceLocator.getBean(PublicacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Publicacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = this.publicacoes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Publicacao publicacao = (Publicacao) value;
			return publicacao.getId() == null ? null : publicacao.getId().toString();
		}
		
		return "";
	}

}
