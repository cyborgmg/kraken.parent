package com.peixeurbano.kraken.entity.redshift.kraken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the mng_deals_latest database table.
 * 
 */
@Entity
@Table(name="mng_deals_latest", schema="kraken")
/*
@NamedQueries({
	@NamedQuery(name = MngDealsLatest.MNGDEALSLATEST_FINDALL, query = "SELECT m FROM MngDealsLatest m where m.title<>''")    
})
*/
public class MngDealsLatest extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;
	
	public static final String  MNGDEALSLATEST_BASE = "SELECT distinct d from Useraccount u, OcmSitenavigationclick os, MngPermanentSchedulingDeal psds, MngPermanentScheduling ps, MngPagesLatest p, MngDealsLatest d "+
													  " where os.useraccountid=u.useraccountid and os.dealid = d.dealId and trim(d.title)<>'' and ps.numericId = psds.numericId and p.pageid = ps.page and d.dealId = psds.deal ";
	public static final String  MNGDEALSLATEST_QUERY = MngDealsLatest.MNGDEALSLATEST_BASE+"and ( upper(d.title) ";
	public static final String  MNGDEALSLATEST_QUERY_OR = " or upper(d.dealSfNumber) ";
	public static final String  MNGDEALSLATEST_IN_STATE = " and p.pageid like ";
	public static final String  MNGDEALSLATEST_IN = "SELECT distinct d FROM MngDealsLatest d WHERE CONVERT(varchar,d.dealNumericId) in (";

	@Column(name="account_company_name")
	private String accountCompanyName;

	@Column(name="account_name")
	private String accountName;

	@Column(name="deal_format")
	private String dealFormat;

	@Column(name="deal_id")
	private String dealId;

	@Id
	@Column(name="deal_numeric_id")
	private Integer dealNumericId;

	@Column(name="idoferta")
	private Integer idoferta;

	@Column(name="legacydata_unified_discount_id")
	private Integer legacydataUnifiedDiscountId;

	@Column(name="phoenix_category")
	private String phoenixCategory;

	@Column(name="title")
	private String title;
	
	@Column(name="deal_sf_number")
	private String dealSfNumber;
	
	@Column(name="salesforce_contract_id")
	private String salesforceContractId;

	@Column(name="salesforce_deal_id")
	private String salesforceDealId;

	public MngDealsLatest() {
	}

	public String getAccountCompanyName() {
		return this.accountCompanyName;
	}

	public void setAccountCompanyName(final String accountCompanyName) {
		this.accountCompanyName = accountCompanyName;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	public String getDealFormat() {
		return this.dealFormat;
	}

	public void setDealFormat(final String dealFormat) {
		this.dealFormat = dealFormat;
	}

	public String getDealId() {
		return this.dealId;
	}

	public void setDealId(final String dealId) {
		this.dealId = dealId;
	}

	public Integer getDealNumericId() {
		return this.dealNumericId;
	}

	public void setDealNumericId(final Integer dealNumericId) {
		this.dealNumericId = dealNumericId;
	}

	public Integer getIdoferta() {
		return this.idoferta;
	}

	public void setIdoferta(final Integer idoferta) {
		this.idoferta = idoferta;
	}

	public Integer getLegacydataUnifiedDiscountId() {
		return this.legacydataUnifiedDiscountId;
	}

	public void setLegacydataUnifiedDiscountId(final Integer legacydataUnifiedDiscountId) {
		this.legacydataUnifiedDiscountId = legacydataUnifiedDiscountId;
	}

	public String getPhoenixCategory() {
		return this.phoenixCategory;
	}

	public void setPhoenixCategory(final String phoenixCategory) {
		this.phoenixCategory = phoenixCategory;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDealSfNumber() {
		return this.dealSfNumber;
	}

	public void setDealSfNumber(final String dealSfNumber) {
		this.dealSfNumber = dealSfNumber;
	}

	public String getSalesforceContractId() {
		return this.salesforceContractId;
	}

	public void setSalesforceContractId(final String salesforceContractId) {
		this.salesforceContractId = salesforceContractId;
	}

	public String getSalesforceDealId() {
		return this.salesforceDealId;
	}

	public void setSalesforceDealId(final String salesforceDealId) {
		this.salesforceDealId = salesforceDealId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.dealNumericId.toString();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public void setValue(final String nome) {
		this.title = nome;
		
	}
		
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.dealSfNumber;
	}
	
}