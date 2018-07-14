package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngAttributesOption;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;

public class AttributeSelectNavRequest extends SelectRequest<MngAttributesOption,AttributeSelectNav> {

	private static final long serialVersionUID = 1L;
	
	public AttributeSelectNavRequest(final Campaign campaign, final List<AttributeSelectNav> campaignList) {
		super(campaign, campaignList);
	}

}
