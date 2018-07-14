package com.peixeurbano.kraken.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peixeurbano.kraken.entity.sqlserver.Agerange;
import com.peixeurbano.kraken.interfaces.AgerangeDaoRemote;


@Stateless(name = "AgerangeDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class AgerangeDao implements AgerangeDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(AgerangeDao.class);
	
	@Override
	public List<Agerange> getAll(){
		try {
			return this.entityManager.createNamedQuery(Agerange.AGERANGE_FINDALL).getResultList();
		} catch (final PersistenceException e) {
            this.log.info(e.getMessage(), e);
            return new ArrayList<Agerange>(0);
		}
			
	}

}
