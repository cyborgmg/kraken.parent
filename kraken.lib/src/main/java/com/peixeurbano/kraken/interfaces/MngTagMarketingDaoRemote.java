package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;

@Remote
public interface MngTagMarketingDaoRemote {

	List<MngTag> getQuery(String condition);

	List<MngTag> getSourceNames(final List<CollectionsSelectNav> targets);

}
