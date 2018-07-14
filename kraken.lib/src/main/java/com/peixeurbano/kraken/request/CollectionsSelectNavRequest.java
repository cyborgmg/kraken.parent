package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;

public class CollectionsSelectNavRequest extends SelectRequest<MngTag, CollectionsSelectNav> {

	private static final long serialVersionUID = 1L;
	
	public CollectionsSelectNavRequest(final Campaign campaign, final List<CollectionsSelectNav> campaignList) {
		super(campaign, campaignList);
	}

}
