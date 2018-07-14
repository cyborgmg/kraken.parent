package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the CAMPAIGN_USERACCOUNT database table.
 * 
 */
@Entity
@Table(name="CAMPAIGN_USERACCOUNT")
@NamedQueries({
	@NamedQuery(name=CampaignUseraccount.CAMPAIGN_USERACCOUNT_COUNT_BY_CAMPAIN_EXCLUDE_ID_AND_USERACCOUNTID, query="SELECT COUNT(c) FROM CampaignUseraccount c WHERE "
			+ " c.campaign.campaignId = :campaignId AND useraccountId = :useraccountId " ),
	@NamedQuery(name=CampaignUseraccount.CAMPAIGN_USERACCOUNT_COUNT_BY_CAMPAIN_ID, query="SELECT c.useraccountId FROM CampaignUseraccount c WHERE "
			+ " c.campaign.campaignId = :campaignId " )	
})
public class CampaignUseraccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int MAXSIZE = 800000;
	
	public static final int MAX_INSERT_SIZE = 500;
	
	public static final String CAMPAIGN_USERACCOUNT_COUNT_BY_CAMPAIN_EXCLUDE_ID_AND_USERACCOUNTID = "campaign.useraccount.by.campain.exclude.id.and.useraccountid";
	
	public static final String CAMPAIGN_USERACCOUNT_COUNT_BY_CAMPAIN_ID = "campaign.useraccount.count.by.campain.id";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAMPAIGN_USERACCOUNT_ID")
	private Integer campaignUseraccountId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	//bi-directional many-to-one association to Useraccount
	@Column(name="USERACCOUNTID")
	private Integer useraccountId;

	public CampaignUseraccount() {
	}
	
	public CampaignUseraccount(final Campaign campaign, final Integer useraccountId) {
		super();
		this.campaign = campaign;
		this.useraccountId = useraccountId;
	}

	public Integer getCampaignUseraccountId() {
		return this.campaignUseraccountId;
	}

	public void setCampaignUseraccountId(final Integer campaignUseraccountId) {
		this.campaignUseraccountId = campaignUseraccountId;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public Integer getUseraccount() {
		return this.useraccountId;
	}

	public void setUseraccount(final Integer useraccountId) {
		this.useraccountId = useraccountId;
	}

}