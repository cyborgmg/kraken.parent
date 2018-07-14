package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.Unifieddiscount;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;

public class OfferSelectRequest extends SelectRequest<Unifieddiscount, OffersSelect> {

	private static final long serialVersionUID = 1L;
	
	public OfferSelectRequest(final Campaign campaign, final List<OffersSelect> campaignList) {
		super(campaign, campaignList);
	}

}
