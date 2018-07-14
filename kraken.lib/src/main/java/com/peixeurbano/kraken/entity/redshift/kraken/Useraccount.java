package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the useraccount database table.
 * 
 */
@Entity
@Table(name="useraccount", schema="kraken")
public class Useraccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="useraccountid")
	private Integer useraccountid;
	
	@Column(name="agerangeid")
	private short agerangeid;

	
	@Column(name="anniversary")
	private Timestamp anniversary;

	@Column(name="countofvalepresente")
	private Integer countofvalepresente;

	
	@Column(name="datefirstpurchase")
	private Timestamp datefirstpurchase;

	
	@Column(name="datelastpurchase")
	private Timestamp datelastpurchase;

	
	@Column(name="dateregistered")
	private Timestamp dateregistered;

	@Column(name="engagement")
	private Integer engagement;

	@Column(name="female")
	private Short female;

	@Column(name="haspurbemestar")
	private Boolean haspurbemestar;

	@Column(name="haspure_commerce")
	private Boolean haspureCommerce;

	@Column(name="haspurentretenimento")
	private Boolean haspurentretenimento;

	@Column(name="haspurgastronomia")
	private Boolean haspurgastronomia;

	@Column(name="haspurservico_local")
	private Boolean haspurservicoLocal;

	@Column(name="haspurturismo")
	private Boolean haspurturismo;

	@Column(name="maximumpurchasevalue")
	private BigDecimal maximumpurchasevalue;

	@Column(name="netrevenue")
	private BigDecimal netrevenue;

	@Column(name="promocodes_count")
	private Long promocodesCount;

	@Column(name="totalpurchases")
	private BigDecimal totalpurchases;

	@Column(name="usesfacebooklogin")
	private short usesfacebooklogin;

	public Useraccount() {
	}

	public short getAgerangeid() {
		return this.agerangeid;
	}

	public void setAgerangeid(final short agerangeid) {
		this.agerangeid = agerangeid;
	}

	public Timestamp getAnniversary() {
		return this.anniversary;
	}

	public void setAnniversary(final Timestamp anniversary) {
		this.anniversary = anniversary;
	}

	public Integer getCountofvalepresente() {
		return this.countofvalepresente;
	}

	public void setCountofvalepresente(final Integer countofvalepresente) {
		this.countofvalepresente = countofvalepresente;
	}

	public Timestamp getDatefirstpurchase() {
		return this.datefirstpurchase;
	}

	public void setDatefirstpurchase(final Timestamp datefirstpurchase) {
		this.datefirstpurchase = datefirstpurchase;
	}

	public Timestamp getDatelastpurchase() {
		return this.datelastpurchase;
	}

	public void setDatelastpurchase(final Timestamp datelastpurchase) {
		this.datelastpurchase = datelastpurchase;
	}

	public Timestamp getDateregistered() {
		return this.dateregistered;
	}

	public void setDateregistered(final Timestamp dateregistered) {
		this.dateregistered = dateregistered;
	}

	public Integer getEngagement() {
		return this.engagement;
	}

	public void setEngagement(final Integer engagement) {
		this.engagement = engagement;
	}

	public Short getFemale() {
		return this.female;
	}

	public void setFemale(final Short female) {
		this.female = female;
	}

	public Boolean getHaspurbemestar() {
		return this.haspurbemestar;
	}

	public void setHaspurbemestar(final Boolean haspurbemestar) {
		this.haspurbemestar = haspurbemestar;
	}

	public Boolean getHaspureCommerce() {
		return this.haspureCommerce;
	}

	public void setHaspureCommerce(final Boolean haspureCommerce) {
		this.haspureCommerce = haspureCommerce;
	}

	public Boolean getHaspurentretenimento() {
		return this.haspurentretenimento;
	}

	public void setHaspurentretenimento(final Boolean haspurentretenimento) {
		this.haspurentretenimento = haspurentretenimento;
	}

	public Boolean getHaspurgastronomia() {
		return this.haspurgastronomia;
	}

	public void setHaspurgastronomia(final Boolean haspurgastronomia) {
		this.haspurgastronomia = haspurgastronomia;
	}

	public Boolean getHaspurservicoLocal() {
		return this.haspurservicoLocal;
	}

	public void setHaspurservicoLocal(final Boolean haspurservicoLocal) {
		this.haspurservicoLocal = haspurservicoLocal;
	}

	public Boolean getHaspurturismo() {
		return this.haspurturismo;
	}

	public void setHaspurturismo(final Boolean haspurturismo) {
		this.haspurturismo = haspurturismo;
	}

	public BigDecimal getMaximumpurchasevalue() {
		return this.maximumpurchasevalue;
	}

	public void setMaximumpurchasevalue(final BigDecimal maximumpurchasevalue) {
		this.maximumpurchasevalue = maximumpurchasevalue;
	}

	public BigDecimal getNetrevenue() {
		return this.netrevenue;
	}

	public void setNetrevenue(final BigDecimal netrevenue) {
		this.netrevenue = netrevenue;
	}

	public Long getPromocodesCount() {
		return this.promocodesCount;
	}

	public void setPromocodesCount(final Long promocodesCount) {
		this.promocodesCount = promocodesCount;
	}

	public BigDecimal getTotalpurchases() {
		return this.totalpurchases;
	}

	public void setTotalpurchases(final BigDecimal totalpurchases) {
		this.totalpurchases = totalpurchases;
	}

	public Integer getUseraccountid() {
		return this.useraccountid;
	}

	public void setUseraccountid(final Integer useraccountid) {
		this.useraccountid = useraccountid;
	}

	public short getUsesfacebooklogin() {
		return this.usesfacebooklogin;
	}

	public void setUsesfacebooklogin(final short usesfacebooklogin) {
		this.usesfacebooklogin = usesfacebooklogin;
	}

}