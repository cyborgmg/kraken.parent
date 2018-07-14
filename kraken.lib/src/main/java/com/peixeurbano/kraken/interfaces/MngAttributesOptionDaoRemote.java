package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.MngAttributesOption;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;

@Remote
public interface MngAttributesOptionDaoRemote {

	List<MngAttributesOption> getQuery(String condition);

	List<MngAttributesOption> getSourceNames(final List<AttributeSelectNav> targets);

}
