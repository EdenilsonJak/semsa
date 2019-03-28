package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.ModalidadeLicitacao;
import com.ejsistemas.semsa.repository.ModalidadeLicitacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;


@FacesConverter(forClass=ModalidadeLicitacao.class)
public class ModalidadeLicitacaoConverter implements Converter{
	
	private ModalidadeLicitacaoRepository modalidades;

	public ModalidadeLicitacaoConverter(){
		this.modalidades = (ModalidadeLicitacaoRepository) CDIServiceLocator.getBean(ModalidadeLicitacaoRepository.class);
	}
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ModalidadeLicitacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = this.modalidades.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ModalidadeLicitacao modalidadeLicitacao = (ModalidadeLicitacao) value;
			return modalidadeLicitacao.getId() == null ? null : modalidadeLicitacao.getId().toString();
		}
		return "";
	}

}
