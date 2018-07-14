package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the sales_source database table.
 * 
 */
@Entity
@Table(name="sales_source", schema="kraken")
public class SalesSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="krakenid")
	private String krakenid;
	
	@Column(name="sourceid")
	private short sourceid;
	
	@Column(name="count")
	private Long count;
	
	@Column(name="useraccountid")
	private Integer useraccountid;

	public SalesSource() {
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(final Long count) {
		this.count = count;
	}

	public short getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(final short sourceid) {
		this.sourceid = sourceid;
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