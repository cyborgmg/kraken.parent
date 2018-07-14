package com.peixeurbano.kraken.entity.redshift.kraken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the mng_attributes_options database table.
 * 
 */
@Entity
@Table(name="mng_attributes_options", schema="kraken")
@NamedQuery(name="MngAttributesOption.findAll", query="SELECT m FROM MngAttributesOption m")
public class MngAttributesOption  extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;

	public static final String  MNGATTRIBUTE_BASE = "select distinct new MngAttributesOption(matto.idAtt, matto.krakenId, matto.name, matto.slug, mdla.attribute) "
				+ "from Useraccount u, OcmSitenavigationclick os, MngAttribute matt, MngAttributesOption matto, MngDealsLatestAttribute mdla where "
				+" os.useraccountid=u.useraccountid and matt.id=matto.idAtt and matto.slug=mdla.attribute and mdla.dealId=os.dealid ";														
	public static final String  MNGATTRIBUTE_QUERY = MngAttributesOption.MNGATTRIBUTE_BASE+" and upper(matto.name) ";
	public static final String  MNGATTRIBUTE_IN = MngAttributesOption.MNGATTRIBUTE_BASE+" and matto.krakenId in (";

	
	@Column(name="id")
	private String idAtt;

	@Id
	@Column(name="kraken_id")
	private String krakenId;

	@Column(name="name")
	private String name;

	@Column(name="slug")
	private String slug;
	/*
	@Formula("(select max(dla.attribute) from kraken.mng_attributes a, kraken.mng_attributes_options ao, kraken.mng_deals_latest_attributes dla where a.id=ao.id and ao.slug=dla.attribute and ao.kraken_id=kraken_id)")  
	@Basic(fetch=FetchType.LAZY)
	*/
	@Transient
	private String attribute;

	public MngAttributesOption() {
	}
	
	

	public MngAttributesOption(final String idAtt, final String krakenId, final String name, final String slug, final String attribute) {
		super();
		this.idAtt = idAtt;
		this.krakenId = krakenId;
		this.name = name;
		this.slug = slug;
		this.attribute = attribute;
	}



	public String getIdAtt() {
		return this.idAtt;
	}

	public void setIdAtt(final String idAtt) {
		this.idAtt = idAtt;
	}

	public String getKrakenId() {
		return this.krakenId;
	}

	public void setKrakenId(final String krakenId) {
		this.krakenId = krakenId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(final String slug) {
		this.slug = slug;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.getAttribute();
	}

	@Override
	public void setValue(final String nome) {
		// TODO Auto-generated method stub
		this.name = nome;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.krakenId;
	}

}