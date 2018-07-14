package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelect;

public class CategorySelectRequest extends SelectRequest<MngTag, CategorySelect> {

	private static final long serialVersionUID = 1L;
	
	public CategorySelectRequest(final Campaign campaign, final List<CategorySelect> campaignList) {
		super(campaign, campaignList);
	}

}
