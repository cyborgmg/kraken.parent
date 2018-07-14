package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the mailinglist_useraccount database table.
 * 
 */
@Entity
@Table(name="mailinglist_useraccount", schema="kraken")
public class MailinglistUseraccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="krakenid")
	private String krakenid;
	
	@Column(name="mailinglistid")
	private Integer mailinglistid;

	@Column(name="useraccountid")
	private Integer useraccountid;

	public MailinglistUseraccount() {
	}

	public Integer getMailinglistid() {
		return this.mailinglistid;
	}

	public void setMailinglistid(final Integer mailinglistid) {
		this.mailinglistid = mailinglistid;
	}

	public Integer getUseraccountid() {
		return this.useraccountid;
	}

	public void setUseraccountid(final Integer useraccountid) {
		this.useraccountid = useraccountid;
	}

	public String getKrakenid() {
		return this.krakenid;
	}

	public void setKrakenid(final String krakenid) {
		this.krakenid = krakenid;
	}

}