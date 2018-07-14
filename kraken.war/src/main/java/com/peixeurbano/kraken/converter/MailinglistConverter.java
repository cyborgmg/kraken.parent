package com.peixeurbano.kraken.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.redshift.kraken.Mailinglist;



@FacesConverter(forClass = Mailinglist.class)
public class MailinglistConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String value) {
      if (value != null && !value.isEmpty()) {
          return uiComponent.getAttributes().get(value);
      }
      return null;
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object value) {
      if (value instanceof Mailinglist) {
    	  Mailinglist entity= (Mailinglist) value;
          if (entity != null && entity instanceof Mailinglist && entity.getMailinglistid() != null) {
              uiComponent.getAttributes().put( entity.getMailinglistid().toString(), entity);
              return entity.getMailinglistid().toString();
          }
      }
      return "";
  }

}
