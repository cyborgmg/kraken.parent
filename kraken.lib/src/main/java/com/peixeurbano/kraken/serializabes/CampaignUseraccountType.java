package com.peixeurbano.kraken.serializabes;

import java.io.Serializable;
import java.sql.Timestamp;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;

public class CampaignUseraccountType implements Serializable {
	
	
	private Campaign campaign;
	private Timestamp dateini;
	private StringBuilder qry;
	private Integer size;
	private String mail;
	private String cc;
	
	public CampaignUseraccountType(final Campaign campaign, final Timestamp dateini, final StringBuilder qry, final Integer size, final String mail, final String cc) {
		super();
		
		this.campaign = campaign;
		this.dateini = dateini;
		this.qry = qry;
		this.size = size;
		this.mail=mail;
		this.cc = cc;
	}
	
	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public Timestamp getDateini() {
		return this.dateini;
	}

	public void setDateini(final Timestamp dateini) {
		this.dateini = dateini;
	}

	public StringBuilder getQry() {
		return this.qry;
	}

	public void setQry(final StringBuilder qry) {
		this.qry = qry;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(final Integer size) {
		this.size = size;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(final String cc) {
		this.cc = cc;
	}

}
