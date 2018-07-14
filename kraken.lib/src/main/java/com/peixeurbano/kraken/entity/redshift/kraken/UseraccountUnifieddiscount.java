package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the useraccount_unifieddiscount database table.
 * 
 */
@Entity
@Table(name="useraccount_unifieddiscount", schema="kraken")
@NamedQuery(name="UseraccountUnifieddiscount.findAll", query="SELECT u FROM UseraccountUnifieddiscount u")
public class UseraccountUnifieddiscount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="krakenid")
	private String krakenid;
	
	@Column(name="count")
	private Long count;

	@Column(name="unifieddiscountid")
	private Integer unifieddiscountid;

	@Column(name="useraccountid")
	private Integer useraccountid;

	public UseraccountUnifieddiscount() {
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(final Long count) {
		this.count = count;
	}

	public Integer getUnifieddiscountid() {
		return this.unifieddiscountid;
	}

	public void setUnifieddiscountid(final Integer unifieddiscountid) {
		this.unifieddiscountid = unifieddiscountid;
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