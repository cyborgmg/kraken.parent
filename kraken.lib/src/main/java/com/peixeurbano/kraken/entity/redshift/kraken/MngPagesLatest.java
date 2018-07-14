package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mng_pages_latest database table.
 * 
 */
@Entity
@Table(name="mng_pages_latest", schema="kraken")
@NamedQuery(name="MngPagesLatest.findAll", query="SELECT m FROM MngPagesLatest m")
public class MngPagesLatest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="buildversion")
	private String buildversion;

	@Column(name="closedpage")
	private Boolean closedpage;

	@Column(name="disable_subscribe")
	private Boolean disableSubscribe;

	@Column(name="headerimage")
	private String headerimage;

	@Column(name="help_phone_number")
	private String helpPhoneNumber;

	@Id
	@Column(name="id")
	private String id;

	@Column(name="isdeleted")
	private Boolean isdeleted;

	@Column(name="lastmodified")
	private Timestamp lastmodified;

	@Column(name="lastmodifiedby")
	private String lastmodifiedby;

	@Column(name="legacyregionid")
	private Integer legacyregionid;

	@Column(name="mobile")
	private Boolean mobile;

	@Column(name="navigable")
	private Boolean navigable;

	@Column(name="numeric_id")
	private Integer numericId;

	@Column(name="pageid")
	private String pageid;

	@Column(name="pagetype")
	private String pagetype;

	@Column(name="popular")
	private Boolean popular;

	@Column(name="public")
	private Boolean publico;

	@Column(name="show_adwords")
	private Boolean showAdwords;

	@Column(name="show_help")
	private Boolean showHelp;

	@Column(name="timezoneid")
	private String timezoneid;

	@Column(name="title")
	private String title;

	@Column(name="version")
	private String version;

	@Column(name="welcome")
	private String welcome;

	public MngPagesLatest() {
	}

	public String getBuildversion() {
		return this.buildversion;
	}

	public void setBuildversion(final String buildversion) {
		this.buildversion = buildversion;
	}

	public Boolean getClosedpage() {
		return this.closedpage;
	}

	public void setClosedpage(final Boolean closedpage) {
		this.closedpage = closedpage;
	}

	public Boolean getDisableSubscribe() {
		return this.disableSubscribe;
	}

	public void setDisableSubscribe(final Boolean disableSubscribe) {
		this.disableSubscribe = disableSubscribe;
	}

	public String getHeaderimage() {
		return this.headerimage;
	}

	public void setHeaderimage(final String headerimage) {
		this.headerimage = headerimage;
	}

	public String getHelpPhoneNumber() {
		return this.helpPhoneNumber;
	}

	public void setHelpPhoneNumber(final String helpPhoneNumber) {
		this.helpPhoneNumber = helpPhoneNumber;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(final Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Timestamp getLastmodified() {
		return this.lastmodified;
	}

	public void setLastmodified(final Timestamp lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(final String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public Integer getLegacyregionid() {
		return this.legacyregionid;
	}

	public void setLegacyregionid(final Integer legacyregionid) {
		this.legacyregionid = legacyregionid;
	}

	public Boolean getMobile() {
		return this.mobile;
	}

	public void setMobile(final Boolean mobile) {
		this.mobile = mobile;
	}

	public Boolean getNavigable() {
		return this.navigable;
	}

	public void setNavigable(final Boolean navigable) {
		this.navigable = navigable;
	}

	public Integer getNumericId() {
		return this.numericId;
	}

	public void setNumericId(final Integer numericId) {
		this.numericId = numericId;
	}

	public String getPageid() {
		return this.pageid;
	}

	public void setPageid(final String pageid) {
		this.pageid = pageid;
	}

	public String getPagetype() {
		return this.pagetype;
	}

	public void setPagetype(final String pagetype) {
		this.pagetype = pagetype;
	}

	public Boolean getPopular() {
		return this.popular;
	}

	public void setPopular(final Boolean popular) {
		this.popular = popular;
	}

	public Boolean getPublic_() {
		return this.publico;
	}

	public void setPublic_(final Boolean publico) {
		this.publico = publico;
	}

	public Boolean getShowAdwords() {
		return this.showAdwords;
	}

	public void setShowAdwords(final Boolean showAdwords) {
		this.showAdwords = showAdwords;
	}

	public Boolean getShowHelp() {
		return this.showHelp;
	}

	public void setShowHelp(final Boolean showHelp) {
		this.showHelp = showHelp;
	}

	public String getTimezoneid() {
		return this.timezoneid;
	}

	public void setTimezoneid(final String timezoneid) {
		this.timezoneid = timezoneid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getWelcome() {
		return this.welcome;
	}

	public void setWelcome(final String welcome) {
		this.welcome = welcome;
	}

}