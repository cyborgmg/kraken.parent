package com.peixeurbano.kraken.entity.redshift.kraken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;



/**
 * The persistent class for the unifieddiscount database table.
 * 
 */
@Entity
@Table(name="unifieddiscount", schema="kraken")
/*
@NamedQueries({
    @NamedQuery(name = Unifieddiscount.UNIFIEDDISCOUNT_FINDALL, query = "SELECT u FROM Unifieddiscount u")
})
*/
public class Unifieddiscount extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;
	/*
	public static final String  UNIFIEDDISCOUNT_BASE = "SELECT distinct ud FROM Useraccount u, UseraccountUnifieddiscount o, Unifieddiscount ud, MngDealsLatest d, "
													+" MngPermanentSchedulingDeal psds, MngPermanentScheduling ps, MngPagesLatest p "
													+" where u.useraccountid=o.useraccountid and o.unifieddiscountid=ud.unifieddiscountid "
													+" and o.unifieddiscountid = d.legacydataUnifiedDiscountId and psds.deal = d.dealId "
													+" and ps.numericId = psds.numericId and ps.page = p.pageid ";
	*/
	public static final String  UNIFIEDDISCOUNT_BASE = "SELECT distinct ud FROM Useraccount u, UseraccountUnifieddiscount o, Unifieddiscount ud"
			+" where u.useraccountid=o.useraccountid and o.unifieddiscountid=ud.unifieddiscountid ";
	
	public static final String  UNIFIEDDISCOUNT_QUERY = Unifieddiscount.UNIFIEDDISCOUNT_BASE+" and upper(ud.unifieddiscountnameoriginal) ";
	
	//public static final String  UNIFIEDDISCOUNT_IN_STATE = " and p.pageid like ";
	
	public static final String  UNIFIEDDISCOUNT_IN = "SELECT distinct ud FROM Unifieddiscount ud WHERE ud.unifieddiscountid in (";

	@Id
	@Column(name="unifieddiscountid")
	private Integer unifieddiscountid;

	@Column(name="unifieddiscountnameoriginal")
	private String unifieddiscountnameoriginal;
/*	
	@Transient
	private boolean check;
*/
	public Unifieddiscount() {
	}

	public Integer getUnifieddiscountid() {
		return this.unifieddiscountid;
	}

	public void setUnifieddiscountid(final Integer unifieddiscountid) {
		this.unifieddiscountid = unifieddiscountid;
	}

	public String getUnifieddiscountnameoriginal() {
		return this.unifieddiscountnameoriginal;
	}

	public void setUnifieddiscountnameoriginal(final String unifieddiscountnameoriginal) {
		this.unifieddiscountnameoriginal = unifieddiscountnameoriginal;
	}
/*
	public boolean isCheck() {
		return this.check;
	}

	public void setCheck(final boolean check) {
		this.check = check;
	}
*/	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.unifieddiscountid.toString();
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