package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

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


/**
 * The persistent class for the MAILINGLIST_SELECT database table.
 * 
 */
@Entity
@Table(name="MAILINGLIST_SELECT")
@NamedQuery(name="MailinglistSelect.findAll", query="SELECT m FROM MailinglistSelect m")
public class MailinglistSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MAIL_ID")
	private Integer mailId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	@Column(name="MAILINGLIST_ID")
	private Integer mailinglistId;
	
	@Transient
	private String mailinglistname;

	public MailinglistSelect() {
	}

	public MailinglistSelect(final Campaign campaign) {
		super();
		this.campaign = campaign;
	}

	public MailinglistSelect(final Integer mailId, final Campaign campaign, final Integer mailinglistId, final String mailinglistname) {
		super();
		this.mailId = mailId;
		this.campaign = campaign;
		this.mailinglistId = mailinglistId;
		this.mailinglistname = mailinglistname;
	}

	public Integer getMailId() {
		return this.mailId;
	}

	public void setMailId(final Integer mailId) {
		this.mailId = mailId;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public Integer getMailinglistId() {
		return this.mailinglistId;
	}

	public void setMailinglistId(final Integer mailinglistId) {
		this.mailinglistId = mailinglistId;
	}

	public String getMailinglistname() {
		return this.mailinglistname;
	}

	public void setMailinglistname(final String mailinglistname) {
		this.mailinglistname = mailinglistname;
	}

}