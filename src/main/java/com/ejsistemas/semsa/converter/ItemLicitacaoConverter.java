package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.ItemLicitacao;
import com.ejsistemas.semsa.repository.ItemLicitacaoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ItemLicitacao.class)
public class ItemLicitacaoConverter implements Converter{
	
	private ItemLicitacaoRepository itemLicitacaoRepository;
	
	public ItemLicitacaoConverter(){
		itemLicitacaoRepository = CDIServiceLocator.getBean(ItemLicitacaoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ItemLicitacao retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = itemLicitacaoRepository.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			ItemLicitacao itemLicitaca = (ItemLicitacao) value;
			return itemLicitaca.getId() == null ? null : itemLicitaca.getId().toString();
		}
		
		return "";
	}

}
