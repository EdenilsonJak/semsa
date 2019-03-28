package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.dvs.AtividadeEconomica;
import com.ejsistemas.semsa.repository.dvs.AtividadeRepository;
import com.ejsistemas.semsa.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=AtividadeEconomica.class)
public class EconomicaConverter implements Converter {

	private AtividadeRepository atividadeRepository;
	
	public EconomicaConverter() {
		atividadeRepository = CDIServiceLocator.getBean(AtividadeRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		AtividadeEconomica retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = atividadeRepository.porId(id);
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {		
		if(value != null){
			AtividadeEconomica economica = (AtividadeEconomica) value;
			return economica.getCdatividadeeconomica() == null ? null : economica.getCdatividadeeconomica().toString();
		}
		return "";
	}
	
}
