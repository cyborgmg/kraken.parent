package com.peixeurbano.kraken.entity.sqlserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the CAMPAIGN_EXCLUDE database table.
 * 
 */
@Entity
@Table(name="CAMPAIGN_EXCLUDE")


public class CampaignExclude extends BtnDlgSelecaoQry {
	
	private static final long serialVersionUID = 1L;
	
	public static final String CAMPAIGN_EXCLUDE_BASE = "SELECT distinct new Campaign(campaignId, name) FROM Campaign c WHERE usable=1 ";
	public static final String CAMPAIGN_EXCLUDE_QUERY = CampaignExclude.CAMPAIGN_EXCLUDE_BASE+" AND upper(c.name) ";
	public static final String CAMPAIGN_EXCLUDE_IN = CampaignExclude.CAMPAIGN_EXCLUDE_BASE+" AND CONVERT(varchar,c.campaignId) in (";
	public static final String CAMPAIGN_EXCLUDE_NOT_IN = " AND c.campaignId <> ";
	public static final String CAMPAIGN_EXCLUDE_QUERY_NOT_IN = " AND c.campaignId NOT IN ( select ce.campaign.campaignId from CampaignExclude ce where ce.campaignIdExc = ";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAMPAIGN_EXCLUDE_ID")
	private Integer campaignExcludeId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	@Column(name="CAMPAIGN_ID_EXC")
	private Integer campaignIdExc;
	
	@Transient
	private String nome; 

	public CampaignExclude() {
	}
	
	public CampaignExclude(final Integer campaignExcludeId, final Campaign campaign, final String campaignId, final String nome) {
		super();
		this.campaignExcludeId=campaignExcludeId;
		this.campaign = campaign;
		this.campaignIdExc = Integer.valueOf(campaignId);
		this.nome=nome;
	}

	public Integer getCampaignExcludeId() {
		return this.campaignExcludeId;
	}

	public void setCampaignExcludeId(final Integer campaignExcludeId) {
		this.campaignExcludeId = campaignExcludeId;
	}


	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}	
	
	public Integer getCampaignIdExc() {
		return this.campaignIdExc;
	}

	public void setCampaignIdExc(final Integer campaignIdExc) {
		this.campaignIdExc = campaignIdExc;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.campaignIdExc.toString();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(final String nome) {
		// TODO Auto-generated method stub
		this.nome = nome;
	}



}