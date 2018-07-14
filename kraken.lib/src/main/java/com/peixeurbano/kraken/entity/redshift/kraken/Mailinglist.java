package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mailinglist database table.
 * 
 */
@Entity
@Table(name="mailinglist", schema="kraken")
@NamedQueries({
    @NamedQuery(name = Mailinglist.MAILINGLIST_FINDALL, query = "select n from Mailinglist n order by n.mailinglistname")
})
public class Mailinglist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String  MAILINGLIST_FINDALL = "Mailinglist.findAll";

	@Id
	@Column(name="mailinglistid")
	private Integer mailinglistid;

	@Column(name="mailinglistname")
	private String mailinglistname;

	public Mailinglist() {
	}

	public Integer getMailinglistid() {
		return this.mailinglistid;
	}

	public void setMailinglistid(final Integer mailinglistid) {
		this.mailinglistid = mailinglistid;
	}

	public String getMailinglistname() {
		return this.mailinglistname;
	}

	public void setMailinglistname(final String mailinglistname) {
		this.mailinglistname = mailinglistname;
	}

}