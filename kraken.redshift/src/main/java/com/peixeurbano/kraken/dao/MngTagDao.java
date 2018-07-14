package com.peixeurbano.kraken.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;
import com.peixeurbano.kraken.interfaces.MngTagDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "MngTagDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class MngTagDao extends AbstractDao<MngTag,CategorySelectNav> implements MngTagDaoRemote {
	
	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager(), MngTag.MNGTAG_TAG_QUERY, null, MngTag.MNGTAG_TAG_IN);
	}
}
