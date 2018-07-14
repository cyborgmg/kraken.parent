package com.peixeurbano.kraken.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.sqlserver.Agerange;


@FacesConverter(forClass = Agerange.class)
public class AgerangeConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof Agerange) {
    	  Agerange entity= (Agerange) value;
          if (entity != null && entity instanceof Agerange && entity.getAgeId() != null) {
              uiComponent.getAttributes().put( entity.getAgeId().toString(), entity);
              return entity.getAgeId().toString();
          }
      }
      return "";
  }

}
