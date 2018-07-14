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

import com.peixeurbano.kraken.entity.redshift.kraken.Mailinglist;
import com.peixeurbano.kraken.interfaces.MailingListDaoRemote;



@Stateless(name = "MailingListDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class MailingListDao implements MailingListDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.redshift-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(MailingListDao.class);
	
	@Override
	public List<Mailinglist> getAll(){
		try {
			return this.entityManager.createNamedQuery(Mailinglist.MAILINGLIST_FINDALL).getResultList();
		} catch (final PersistenceException e) {
            this.log.info(e.getMessage(), e);
            return new ArrayList<Mailinglist>(0);
		}
			
	}

}
