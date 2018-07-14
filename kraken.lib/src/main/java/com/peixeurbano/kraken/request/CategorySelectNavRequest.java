package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;

public class CategorySelectNavRequest extends SelectRequest<MngTag, CategorySelectNav> {

	private static final long serialVersionUID = 1L;
	
	public CategorySelectNavRequest(final Campaign campaign, final List<CategorySelectNav> campaignList) {
		super(campaign, campaignList);
	}

}
