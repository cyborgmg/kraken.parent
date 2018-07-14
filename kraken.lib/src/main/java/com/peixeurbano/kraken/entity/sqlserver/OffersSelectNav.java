package com.peixeurbano.kraken.entity.sqlserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the OFFERS_SELECT_NAV database table.
 * 
 */
@Entity
@Table(name="OFFERS_SELECT_NAV")
@NamedQuery(name="OffersSelectNav.findAll", query="SELECT o FROM OffersSelectNav o")
public class OffersSelectNav extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OFFERS_SELECT_NAV_ID")
	private Integer offersSelectNavId;

	@Column(name="SOURCE_ID")
	private String sourceid;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;
	
	@Transient
	private String nome;

	public OffersSelectNav() {
	}

	public OffersSelectNav(final Integer offersSelectNavId, final Campaign campaign, final String sourceid, final String nome) {
		super();
		this.offersSelectNavId = offersSelectNavId;
		this.campaign = campaign;
		this.sourceid = sourceid;
		this.nome=nome;
	}

	public Integer getOffersSelectNavId() {
		return this.offersSelectNavId;
	}



	public void setOffersSelectNavId(final Integer offersSelectNavId) {
		this.offersSelectNavId = offersSelectNavId;
	}



	public String getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(final String sourceid) {
		this.sourceid = sourceid;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.sourceid;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.nome;
	}


	@Override
	public void setValue(final String nome) {
		this.nome = nome;
		
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}