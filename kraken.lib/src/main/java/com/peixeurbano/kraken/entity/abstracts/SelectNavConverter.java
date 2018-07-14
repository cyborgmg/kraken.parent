package com.peixeurbano.kraken.entity.abstracts;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public abstract class SelectNavConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof BtnDlgSelecaoQry) {
    	  BtnDlgSelecaoQry entity = (BtnDlgSelecaoQry) value;
          if (entity != null && entity instanceof BtnDlgSelecaoQry && entity.getId() != null) {
              uiComponent.getAttributes().put( entity.getId(), entity);
              return entity.getId();
          }
      }
      return "";
  }  

}
