package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatest;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;

public class OfferSelectNavRequest extends SelectRequest<MngDealsLatest, OffersSelectNav> {

	private static final long serialVersionUID = 1L;
	
	public OfferSelectNavRequest(final Campaign campaign, final List<OffersSelectNav> campaignList) {
		super(campaign, campaignList);
	}

}
