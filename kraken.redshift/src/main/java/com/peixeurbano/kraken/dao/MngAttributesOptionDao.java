package com.peixeurbano.kraken.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.MngAttributesOption;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;
import com.peixeurbano.kraken.interfaces.MngAttributesOptionDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "MngAttributesOptionDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class MngAttributesOptionDao extends AbstractDao<MngAttributesOption,AttributeSelectNav> implements MngAttributesOptionDaoRemote {
	
	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager(),MngAttributesOption.MNGATTRIBUTE_QUERY, null, MngAttributesOption.MNGATTRIBUTE_IN);
	}

}
