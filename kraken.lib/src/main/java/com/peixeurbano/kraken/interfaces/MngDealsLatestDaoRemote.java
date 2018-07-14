package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatest;
import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;

@Remote
public interface MngDealsLatestDaoRemote {

	List<MngDealsLatest> getQuery(String condition, final State state);

	List<MngDealsLatest> getSourceNames(final List<OffersSelectNav> targets);

}