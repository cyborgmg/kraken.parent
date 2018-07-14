package com.peixeurbano.kraken.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;
import com.peixeurbano.kraken.interfaces.MngTagMarketingDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "MngTagMarketingDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class MngTagMarketingDao extends AbstractDao<MngTag,CollectionsSelectNav> implements MngTagMarketingDaoRemote {

	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager(), MngTag.MNGTAG_MARKETING_QUERY, null, MngTag.MNGTAG_MARKETING_IN);
	}
}
