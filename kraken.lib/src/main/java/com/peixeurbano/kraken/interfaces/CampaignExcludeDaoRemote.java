package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;

@Remote
public interface CampaignExcludeDaoRemote {
	
	List<Campaign> getQuery(String condition);
	
	List<Campaign> getQuery(final String condition, final Integer campainId);

	List<Campaign> getSourceNames(final List<CampaignExclude> targets);

}
