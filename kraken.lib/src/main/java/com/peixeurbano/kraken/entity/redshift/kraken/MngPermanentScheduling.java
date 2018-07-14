package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mng_permanent_scheduling database table.
 * 
 */
@Entity
@Table(name="mng_permanent_scheduling", schema="kraken")
@NamedQuery(name="MngPermanentScheduling.findAll", query="SELECT m FROM MngPermanentScheduling m")
public class MngPermanentScheduling implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="buildversion")
	private String buildversion;

	@Id
	@Column(name="id")
	private String id;

	@Column(name="isdeleted")
	private Boolean isdeleted;

	@Column(name="lastmodified")
	private Timestamp lastmodified;

	@Column(name="lastmodifiedby")
	private String lastmodifiedby;

	@Column(name="numeric_id")
	private Integer numericId;

	@Column(name="page")
	private String page;

	@Column(name="version")
	private String version;

	public MngPermanentScheduling() {
	}

	public String getBuildversion() {
		return this.buildversion;
	}

	public void setBuildversion(final String buildversion) {
		this.buildversion = buildversion;
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

	public Integer getNumericId() {
		return this.numericId;
	}

	public void setNumericId(final Integer numericId) {
		this.numericId = numericId;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(final String page) {
		this.page = page;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

}