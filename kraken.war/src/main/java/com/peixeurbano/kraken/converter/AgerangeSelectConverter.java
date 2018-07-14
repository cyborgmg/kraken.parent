package com.peixeurbano.kraken.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.sqlserver.AgerangeSelect;


@FacesConverter(forClass = AgerangeSelect.class)
public class AgerangeSelectConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof AgerangeSelect) {
    	  AgerangeSelect entity= (AgerangeSelect) value;
          if (entity != null && entity instanceof AgerangeSelect && entity.getAgeselId() != null) {
              uiComponent.getAttributes().put( entity.getAgeselId().toString(), entity);
              return entity.getAgeselId().toString();
          }
      }
      return "";
  }

}
