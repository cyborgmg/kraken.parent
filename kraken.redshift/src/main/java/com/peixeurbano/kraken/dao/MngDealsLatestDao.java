package com.peixeurbano.kraken.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatest;
import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;
import com.peixeurbano.kraken.interfaces.MngDealsLatestDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "MngDealsLatestDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class MngDealsLatestDao extends AbstractDao<MngDealsLatest,OffersSelectNav> implements MngDealsLatestDaoRemote {

	EntityManager entityManager = Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager();
	
	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(this.entityManager, MngDealsLatest.MNGDEALSLATEST_QUERY, MngDealsLatest.MNGDEALSLATEST_IN_STATE, MngDealsLatest.MNGDEALSLATEST_IN);
	}
	
	@Override
	public List<MngDealsLatest> getQuery(final String condition, final State state){
		try {
			StringBuilder qry = new StringBuilder(this.getAbstractParameters().getQry() +condition + MngDealsLatest.MNGDEALSLATEST_QUERY_OR +condition+")");
			if(this.getAbstractParameters().getQryState()!=null){
				qry.append(this.getAbstractParameters().getQryState()+"'"+state.getId()+"'");
			}
			return this.entityManager.createQuery(qry.toString()).setMaxResults(50).getResultList();
		} catch (final PersistenceException e) {
			e.printStackTrace();
            return new ArrayList<MngDealsLatest>(0);
		}			
	}
}
