package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the promotionalcode database table.
 * 
 */
@Entity
@Table(name="promotionalcode", schema="kraken")
public class Promotionalcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="krakenid")
	private String krakenid;
	
	@Column(name="code")
	private String code;
	
	@Column(name="useraccountid")
	private Integer useraccountid;
	
	@Column(name="count")
	private Long count;

	public Promotionalcode() {
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(final Long count) {
		this.count = count;
	}

	public String getKrakenid() {
		return this.krakenid;
	}

	public void setKrakenid(final String krakenid) {
		this.krakenid = krakenid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Integer getUseraccountid() {
		return this.useraccountid;
	}

	public void setUseraccountid(final Integer useraccountid) {
		this.useraccountid = useraccountid;
	}
	
	

}