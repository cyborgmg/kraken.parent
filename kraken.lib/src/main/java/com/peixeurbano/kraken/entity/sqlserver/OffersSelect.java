package com.peixeurbano.kraken.entity.sqlserver;

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
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the OFFERS_SELECT database table.
 * 
 */
@Entity
@Table(name="OFFERS_SELECT")
//@NamedQuery(name="OffersSelect.findAll", query="SELECT o FROM OffersSelect o")

@NamedQueries({
	@NamedQuery(name = OffersSelect.CAMPAIGN_FIND_BY_ID, query = "SELECT o FROM OffersSelect o WHERE o.campaign.campaignId = :id"),
})

public class OffersSelect  extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;
	
	public static final String  CAMPAIGN_FIND_BY_ID = "campaign.find.by.id";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OFFERS_ID")
	private Integer offersId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	@Column(name="UNIFIEDDISCOUNT_ID")
	private Integer unifieddiscountId;
	
	@Transient
	private String unifieddiscountnameoriginal;

	public OffersSelect() {
	}

	public OffersSelect(final Integer offersId, final Campaign campaign, final Integer unifieddiscountId, final String unifieddiscountnameoriginal) {
		super();
		this.offersId = offersId;
		this.campaign = campaign;
		this.unifieddiscountId = unifieddiscountId;
		this.unifieddiscountnameoriginal=unifieddiscountnameoriginal;
	}

	public OffersSelect(final Integer offersId, final Campaign campaign, final String unifieddiscountId, final String unifieddiscountnameoriginal) {
		super();
		this.offersId = offersId;
		this.campaign = campaign;
		this.unifieddiscountId = Integer.parseInt( unifieddiscountId );
		this.unifieddiscountnameoriginal=unifieddiscountnameoriginal;
	}

	public Integer getOffersId() {
		return this.offersId;
	}


	public void setOffersId(final Integer offersId) {
		this.offersId = offersId;
	}

	public Integer getUnifieddiscountId() {
		return this.unifieddiscountId;
	}


	public void setUnifieddiscountId(final Integer unifieddiscountId) {
		this.unifieddiscountId = unifieddiscountId;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}


	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public String getUnifieddiscountnameoriginal() {
		return this.unifieddiscountnameoriginal;
	}


	public void setUnifieddiscountnameoriginal(final String unifieddiscountnameoriginal) {
		this.unifieddiscountnameoriginal = unifieddiscountnameoriginal;
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.unifieddiscountId.toString();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.unifieddiscountnameoriginal;
	}


	@Override
	public void setValue(final String nome) {
		this.unifieddiscountnameoriginal = nome;
		
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	

}