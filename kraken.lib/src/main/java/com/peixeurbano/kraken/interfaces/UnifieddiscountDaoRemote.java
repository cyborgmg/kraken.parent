package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.entity.redshift.kraken.Unifieddiscount;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;

@Remote
public interface UnifieddiscountDaoRemote {
	
	List<Unifieddiscount> getQuery(String condition, final State state);

	List<Unifieddiscount> getSourceNames(final List<OffersSelect> targets);

}
