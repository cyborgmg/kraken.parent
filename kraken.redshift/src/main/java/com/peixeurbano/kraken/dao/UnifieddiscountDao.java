package com.peixeurbano.kraken.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.Unifieddiscount;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;
import com.peixeurbano.kraken.interfaces.UnifieddiscountDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;




@Stateless(name = "UnifieddiscountDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class UnifieddiscountDao extends AbstractDao<Unifieddiscount,OffersSelect> implements UnifieddiscountDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.redshift-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(UnifieddiscountDao.class);

	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager(), Unifieddiscount.UNIFIEDDISCOUNT_QUERY, null/*Unifieddiscount.UNIFIEDDISCOUNT_IN_STATE*/, Unifieddiscount.UNIFIEDDISCOUNT_IN);
	}

}
