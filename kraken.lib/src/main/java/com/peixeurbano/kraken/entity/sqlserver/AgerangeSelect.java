package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the AGERANGE_SELECT database table.
 * 
 */
@Entity
@Table(name="AGERANGE_SELECT")
@NamedQuery(name="AgerangeSelect.findAll", query="SELECT a FROM AgerangeSelect a")
public class AgerangeSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AGESEL_ID")
	private Integer ageselId;

	//bi-directional many-to-one association to Agerange
	@ManyToOne
	@JoinColumn(name="AGE_ID")
	private Agerange agerange;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	public AgerangeSelect() {
	}

	public AgerangeSelect(final Campaign campaign) {
		super();
		this.campaign = campaign;
	}

	public AgerangeSelect(final Integer ageselId, final Agerange agerange, final Campaign campaign) {
		super();
		this.ageselId = ageselId;
		this.agerange = agerange;
		this.campaign = campaign;
	}

	public Integer getAgeselId() {
		return this.ageselId;
	}

	public void setAgeselId(final Integer ageselId) {
		this.ageselId = ageselId;
	}

	public Agerange getAgerange() {
		return this.agerange;
	}

	public void setAgerange(final Agerange agerange) {
		this.agerange = agerange;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

}