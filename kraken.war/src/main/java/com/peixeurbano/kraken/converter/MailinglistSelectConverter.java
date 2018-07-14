package com.peixeurbano.kraken.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.sqlserver.MailinglistSelect;


@FacesConverter(forClass = MailinglistSelect.class)
public class MailinglistSelectConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof MailinglistSelect) {
    	  MailinglistSelect entity= (MailinglistSelect) value;
          if (entity != null && entity instanceof MailinglistSelect && entity.getMailId() != null) {
              uiComponent.getAttributes().put( entity.getMailId().toString(), entity);
              return entity.getMailId().toString();
          }
      }
      return "";
  }

}
