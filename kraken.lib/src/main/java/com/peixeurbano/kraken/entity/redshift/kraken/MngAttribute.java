package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the mng_attributes database table.
 * 
 */
@Entity
@Table(name="mng_attributes", schema="kraken")
//@NamedQuery(name="MngAttribute.findAll", query="SELECT m FROM MngAttribute m")
public class MngAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id;

	@Column(name="name")
	private String name;

	public MngAttribute() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}