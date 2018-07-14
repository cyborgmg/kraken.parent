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

import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.interfaces.StateDaoRemote;

@Stateless(name = "StateDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class StateDao implements StateDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.redshift-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(StateDao.class);
	
	@Override
	public List<State> getStates(){
		
		return this.entityManager.createQuery(State.STATE_ALL).getResultList();
		
	}
	
}
