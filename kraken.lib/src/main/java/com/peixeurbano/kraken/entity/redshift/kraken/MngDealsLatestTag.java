package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mng_deals_latest_tags database table.
 * 
 */
@Entity
@Table(name="mng_deals_latest_tags", schema="kraken")
@NamedQuery(name="MngDealsLatestTag.findAll", query="SELECT m FROM MngDealsLatestTag m")
public class MngDealsLatestTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="deal_id")
	private String dealId;

	@Id
	@Column(name="kraken_id")
	private String krakenId;

	@Column(name="tag_description")
	private String tagDescription;

	@Column(name="tag_name")
	private String tagName;

	public MngDealsLatestTag() {
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

	public String getTagDescription() {
		return this.tagDescription;
	}

	public void setTagDescription(final String tagDescription) {
		this.tagDescription = tagDescription;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(final String tagName) {
		this.tagName = tagName;
	}

}