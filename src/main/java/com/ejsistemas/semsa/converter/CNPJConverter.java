package com.ejsistemas.semsa.converter;
import br.com.caelum.stella.format.CNPJFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
/**
 * Converter para CNPJ.
 *
 * Classe base feita pelo Igor gabriel a qual servirá de base para os outros converters
 * próximos converters.
 *
 * @author Igor Gabriel
 */
@FacesConverter("cnpjConverter")
public class CNPJConverter implements Converter {
  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
          throws ConverterException {
    return arg2;
  }
  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
          throws ConverterException {
    if (arg2 == null) {
      return "";
    }
    CNPJFormatter f = new CNPJFormatter();
    return f.format(arg2.toString());
  }
  public static String formataCNPJ(String cnpj) {
    if (cnpj == null || "".equals(cnpj)) {
      return "";
    }
    return new CNPJFormatter().format(cnpj);
  }
}