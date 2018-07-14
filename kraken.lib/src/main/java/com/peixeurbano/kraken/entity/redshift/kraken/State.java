package com.peixeurbano.kraken.entity.redshift.kraken;

import java.io.Serializable;

public class State implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String STATE_ALL = " select distinct new com.peixeurbano.kraken.entity.redshift.kraken.State(p.title||' ('|| ps.page ||')', ps.page) "+
    		" from MngPermanentSchedulingDeal psds ,MngPermanentScheduling ps ,MngPagesLatest p ,MngDealsLatest d "+
    		" where ps.numericId = psds.numericId and p.pageid = ps.page and d.dealId = psds.deal order by 1 ";
	
	private String id;

	private String name;

	public State() {
	}

	public State(final String name, final String id) {
		super();
		this.name = name;
		this.id = id;
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