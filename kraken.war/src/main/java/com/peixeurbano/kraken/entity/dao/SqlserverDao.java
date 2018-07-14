package com.peixeurbano.kraken.entity.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Stateless(name = "SqlserverDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class SqlserverDao {
	
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken-pu");
	private final EntityManager  entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(SqlserverDao.class);

	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
}
