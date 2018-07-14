package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mng_permanent_scheduling_deals database table.
 * 
 */
@Entity
@Table(name="mng_permanent_scheduling_deals", schema="kraken")
@NamedQuery(name="MngPermanentSchedulingDeal.findAll", query="SELECT m FROM MngPermanentSchedulingDeal m")
public class MngPermanentSchedulingDeal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="deal")
	private String deal;

	@Column(name="deal_numeric_id")
	private Integer dealNumericId;

	@Id
	@Column(name="id")
	private String id;

	@Column(name="numeric_id")
	private Integer numericId;

	public MngPermanentSchedulingDeal() {
	}

	public String getDeal() {
		return this.deal;
	}

	public void setDeal(final String deal) {
		this.deal = deal;
	}

	public Integer getDealNumericId() {
		return this.dealNumericId;
	}

	public void setDealNumericId(final Integer dealNumericId) {
		this.dealNumericId = dealNumericId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Integer getNumericId() {
		return this.numericId;
	}

	public void setNumericId(final Integer numericId) {
		this.numericId = numericId;
	}

}