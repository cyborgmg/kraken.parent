package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mng_deals_latest_attributes database table.
 * 
 */
@Entity
@Table(name="mng_deals_latest_attributes", schema="kraken")
@NamedQuery(name="MngDealsLatestAttribute.findAll", query="SELECT m FROM MngDealsLatestAttribute m")
public class MngDealsLatestAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="attribute")
	private String attribute;

	@Column(name="deal_id")
	private String dealId;

	@Id
	@Column(name="kraken_id")
	private String krakenId;

	public MngDealsLatestAttribute() {
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}

	public String getDealId() {
		return this.dealId;
	}

	public void setDealId(final String dealId) {
		this.dealId = dealId;
	}

	public String getKrakenId() {
		return this.krakenId;
	}

	public void setKrakenId(final String krakenId) {
		this.krakenId = krakenId;
	}

}