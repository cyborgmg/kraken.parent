package com.peixeurbano.kraken.entity.redshift.kraken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the useraccount_subcategories database table.
 * 
 */

@Entity
@Table(name="useraccount_subcategories", schema="kraken")
public class UseraccountSubcategory  extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;
	
	public static final String  USERACCOUNTSUBCATEGORY_BASE = "SELECT distinct s from Useraccount u, UseraccountSubcategory s "+
			  										  " where u.useraccountid=s.useraccountid ";
	public static final String  USERACCOUNTSUBCATEGORY_QUERY = USERACCOUNTSUBCATEGORY_BASE+" and upper(s.subcategory) ";	
	public static final String  USERACCOUNTSUBCATEGORY_IN = "SELECT distinct s FROM UseraccountSubcategory s WHERE s.krakenid in (";

	@Id
	@Column(name="krakenid")
	private String krakenid;
	
	@Column(name="subcategory")
	private String subcategory;

	@Column(name="useraccountid")
	private Integer useraccountid;

	public UseraccountSubcategory() {
	}
	
	

	public UseraccountSubcategory(final String krakenid, final Integer useraccountid, final String subcategory) {
		super();
		this.krakenid = krakenid;
		this.subcategory = subcategory;
		this.useraccountid = useraccountid;
	}



	public String getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(final String subcategory) {
		this.subcategory = subcategory;
	}

	public Integer getUseraccountid() {
		return this.useraccountid;
	}

	public void setUseraccountid(final Integer useraccountid) {
		this.useraccountid = useraccountid;
	}

	public String getKrakenId() {
		return this.krakenid;
	}

	public void setKrakenId(final String krakenId) {
		this.krakenid = krakenId;
	}

	@Override
	public String getId() {

		return this.krakenid;
	}

	@Override
	public String getValue() {

		return this.subcategory;
	}

	@Override
	public String getCode() {

		return null;
	}

	@Override
	public void setValue(final String nome) {
		this.subcategory = nome;
		
	}

}