package com.peixeurbano.kraken.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;

@FacesConverter(forClass = OffersSelect.class)
public class OffersSelectConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof OffersSelect) {
    	  OffersSelect entity= (OffersSelect) value;
          if (entity != null && entity instanceof OffersSelect && entity.getOffersId() != null) {
              uiComponent.getAttributes().put( entity.getOffersId().toString(), entity);
              return entity.getOffersId().toString();
          }
      }
      return "";
  }

}
