package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;

@Remote
public interface MngTagDaoRemote {

	List<MngTag> getQuery(String condition);

	List<MngTag> getSourceNames(final List<CategorySelectNav> targets);

}
