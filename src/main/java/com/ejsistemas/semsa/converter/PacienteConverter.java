package com.ejsistemas.semsa.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ejsistemas.semsa.model.Paciente;

@FacesConverter(value = "pacienteConverter")
public class PacienteConverter implements Converter {
	
	/*private PacienteRepository PacienteRepository;
	
	public PacienteConverter(){
		PacienteRepository = CDIServiceLocator.getBean(PacienteRepository.class);
	}*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		/*Paciente retorno = null;*/
		
		if(value != null){
			/*Long id = new Long(value);
			retorno = PacienteRepository.porId(id);*/
			return this.getAttributesFrom(component).get(value);
		}
		return null;	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Paciente paciente = (Paciente) value;
			this.addAttribute(component, paciente);
			return paciente.getId_cliente() == null ? null : paciente.getId_cliente().toString();
		}
		
		return "";
	}

	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}
	
	private void addAttribute(UIComponent component, Paciente paciente){
		this.getAttributesFrom(component).put(paciente.getId_cliente().toString(), paciente);
	}
	
}
