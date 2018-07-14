package com.peixeurbano.kraken.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peixeurbano.kraken.entity.sqlserver.CampaignUseraccount;
import com.peixeurbano.kraken.interfaces.UseraccountDaoRemote;
import com.peixeurbano.kraken.utils.funcoes;

@Stateless(name = "UseraccountDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class UseraccountDao implements UseraccountDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.redshift-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(UseraccountDao.class);
	
	@Override
	public List<Integer> getUseraccounts(final StringBuilder qry) {
		
		return funcoes.cloneList( this.entityManager.createQuery(qry.toString()).setMaxResults(CampaignUseraccount.MAXSIZE).getResultList() );
		
	}

}
