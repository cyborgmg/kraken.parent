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
 * The persistent class for the ATTRIBUTE_SELECT_NAV database table.
 * 
 */
@Entity
@Table(name="ATTRIBUTE_SELECT_NAV")
@NamedQuery(name="AttributeSelectNav.findAll", query="SELECT a FROM AttributeSelectNav a")
public class AttributeSelectNav extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ATTRIBUTE_SELECT_NAV_ID")
	private Integer attributeSelectNavId;

	@Column(name="SOURCE_ID")
	private String sourceid;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;
	
	@Transient
	private String nome;
	
	public AttributeSelectNav(final Integer attributeSelectNavId, final Campaign campaign, final String sourceid, final String nome) {
		super();
		this.attributeSelectNavId = attributeSelectNavId;
		this.campaign = campaign;
		this.sourceid = sourceid;
		this.nome=nome;
	}

	public AttributeSelectNav() {
	}



	public Integer getAttributeSelectNavId() {
		return this.attributeSelectNavId;
	}



	public void setAttributeSelectNavId(final Integer attributeSelectNavId) {
		this.attributeSelectNavId = attributeSelectNavId;
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
		this.nome=nome;
		
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}