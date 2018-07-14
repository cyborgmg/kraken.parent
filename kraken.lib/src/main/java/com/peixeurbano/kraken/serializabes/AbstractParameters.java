package com.peixeurbano.kraken.serializabes;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class AbstractParameters implements Serializable {

	public EntityManager entityManager;
	public String qry;
	public String qryState;
	public String qryIn;
	
	public AbstractParameters(final EntityManager entityManager, final String qry, final String qryState, final String qryIn) {
		super();
		this.entityManager = entityManager;
		this.qry = qry;
		this.qryState = qryState;
		this.qryIn = qryIn;
	}
	public String getQry() {
		return this.qry;
	}
	public void setQry(final String qry) {
		this.qry = qry;
	}
	public String getQryState() {
		return this.qryState;
	}
	public void setQryState(final String qryState) {
		this.qryState = qryState;
	}
	public String getQryIn() {
		return this.qryIn;
	}
	public void setQryIn(final String qryIn) {
		this.qryIn = qryIn;
	}
	
}
