package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.imutable.ListCmpComponent;
import com.peixeurbano.kraken.entity.sqlserver.CmpComponentConfig;

@Remote
public interface CmpComponentDaoRemote {

	List<CmpComponentConfig> getAll();

	ListCmpComponent populateParams();

}
