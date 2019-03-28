package com.ejsistemas.semsa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("app.numbersConverter")
public class AppNumbersConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }

        return value.replaceAll("[^0-9+]", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        return object.toString();
    }

}