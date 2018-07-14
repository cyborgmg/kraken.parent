package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelect;

@Remote
public interface MngTagSelectDaoRemote {

	List<MngTag> getQuery(String condition);

	List<MngTag> getSourceNames(final List<CategorySelect> targets);

}