package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Produto;
import com.ejsistemas.semsa.repository.ProdutoRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	//@Inject
	private ProdutoRepository ProdutoRepository;
	
	public ProdutoConverter(){
		ProdutoRepository = CDIServiceLocator.getBean(ProdutoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if(value != null && value != "" ){
			Long id = new Long(value);
			retorno = ProdutoRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Produto produto = (Produto) value;
 			return produto.getId() == null ? null : produto.getId().toString();
		}
		return "";
	}

}
