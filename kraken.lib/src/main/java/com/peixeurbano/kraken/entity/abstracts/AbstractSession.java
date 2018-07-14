package com.peixeurbano.kraken.entity.abstracts;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.peixeurbano.kraken.entity.enums.Browser;
import com.peixeurbano.kraken.entity.sqlserver.Right;
import com.peixeurbano.kraken.entity.sqlserver.Role;
import com.peixeurbano.kraken.entity.sqlserver.User;

public abstract class AbstractSession implements Serializable {
	
	private static final long serialVersionUID = -8437328085001136786L;
	
	private int size=0;
	
	private User user;
	
	private Integer userId;
	
	public int getSize() {
		return this.size;
	}
	
	public void setSize(final int size) {
		this.size = size;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
		this.userId = this.user.getUserId(); 
	}
	
	public boolean getUserRight(final Integer rightEnum){
		try {
			if(this.getUser()!=null){ 
				for (Role role : this.getUser().getRoles()) {
					for (Right right : role.getRights()) {
						if(right.getRightEnum().getValor()==rightEnum.intValue()){
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public String getMail() {
		if(this.getUser()!=null){
			return "Benvindo: "+this.user.getMail();
		}else{
			return "";
		}
	}
	
	public String getEMail() {
		if(this.getUser()!=null){
			return this.user.getMail();
		}else{
			return "";
		}
	}
	
	public void logout(final ActionEvent ae) {		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "listCampanhas?faces-redirect=true");
        this.setUser(null);
    }

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	public Browser getBrowserName() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

	    if(userAgent.contains(Browser.MSIE.getValor())){ 
	        return Browser.MSIE;
	    }
	    if(userAgent.contains(Browser.FIREFOX.getValor())){ 
	        return Browser.FIREFOX;
	    }
	    if(userAgent.contains(Browser.CHROME.getValor())){ 
	        return Browser.CHROME;
	    }
	    if(userAgent.contains(Browser.OPERA.getValor())){ 
	        return Browser.OPERA;
	    }
	    if(userAgent.contains(Browser.SAFARI.getValor())){ 
	        return Browser.SAFARI;
	    }
	    return Browser.UNKNOWN;
	}	
	

}
