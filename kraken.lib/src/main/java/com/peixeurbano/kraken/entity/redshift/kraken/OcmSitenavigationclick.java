package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ocm_sitenavigationclick database table.
 * 
 */
@Entity
@Table(name="ocm_sitenavigationclick", schema="kraken")
@NamedQuery(name="OcmSitenavigationclick.findAll", query="SELECT o FROM OcmSitenavigationclick o")
public class OcmSitenavigationclick implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="clickid")
	private Integer clickid;

	@Column(name="dealid")
	private String dealid;

	@Column(name="navigationdate")
	private Timestamp navigationdate;

	@Column(name="useraccountid")
	private Integer useraccountid;

	public OcmSitenavigationclick() {
	}

	public Integer getClickid() {
		return this.clickid;
	}

	public void setClickid(final Integer clickid) {
		this.clickid = clickid;
	}

	public String getDealid() {
		return this.dealid;
	}

	public void setDealid(final String dealid) {
		this.dealid = dealid;
	}

	public Timestamp getNavigationdate() {
		return this.navigationdate;
	}

	public void setNavigationdate(final Timestamp navigationdate) {
		this.navigationdate = navigationdate;
	}

	public Integer getUseraccountid() {
		return this.useraccountid;
	}

	public void setUseraccountid(final Integer useraccountid) {
		this.useraccountid = useraccountid;
	}

}