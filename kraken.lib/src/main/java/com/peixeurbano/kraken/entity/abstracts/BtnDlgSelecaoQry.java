package com.peixeurbano.kraken.entity.abstracts;

import java.io.Serializable;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;

public abstract class BtnDlgSelecaoQry implements Serializable {
	private static final long serialVersionUID = 1917236089933753769L;
	
	private Boolean check;
	
	public abstract String getId();
	public abstract String getValue();
	public abstract void setValue(String nome);	
	public abstract String getCode();
	public void constructor(final Integer key, final Campaign campaign, final String id, final String value ){}
	
	public Boolean getCheck() {
		return this.check;
	}
	public void setCheck(final Boolean check) {
		this.check = check;
	}
	
}
