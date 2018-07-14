package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;

public class CampaignExcludeRequest extends SelectRequest<Campaign, CampaignExclude> {

	private static final long serialVersionUID = 1L;
	
	public CampaignExcludeRequest(final Campaign campaign, final List<CampaignExclude> campaignList) {
		super(campaign, campaignList);
	}


}
