package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.UseraccountSubcategory;
import com.peixeurbano.kraken.entity.sqlserver.SubcategorySelect;

@Remote
public interface UseraccountSubcategorysDaoRemote {

	List<UseraccountSubcategory> getQuery(String condition);

	List<UseraccountSubcategory> getSourceNames(List<SubcategorySelect> subcategorySelects);

}
