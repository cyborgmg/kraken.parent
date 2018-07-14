package com.peixeurbano.kraken.request;

import java.util.List;

import com.peixeurbano.kraken.entity.abstracts.SelectRequest;
import com.peixeurbano.kraken.entity.redshift.kraken.UseraccountSubcategory;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.SubcategorySelect;
import com.peixeurbano.kraken.utils.funcoes;

public class SubcategorySelectRequest extends SelectRequest<UseraccountSubcategory, SubcategorySelect> {

	private static final long serialVersionUID = 1L;
	
	public SubcategorySelectRequest(final Campaign campaign, final List<SubcategorySelect> campaignList) {
		super(campaign, campaignList);
	}
	
	//@Override
	public void setSourcenameSubcategorySelects(final List<SubcategorySelect> sources){
		for (SubcategorySelect source : sources) {
			for (SubcategorySelect target : this.getTargets()) {
				//if(target.getId().compareTo(source.getId())==0){
				if(funcoes.asciiToLong(target.getId()).intValue()==funcoes.asciiToLong(source.getId()).intValue()){
					target.setValue(source.getValue());
					break;
				}
			}
		}
	}


}
