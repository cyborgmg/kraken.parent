package com.peixeurbano.kraken.dao;

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

import com.peixeurbano.kraken.entity.sqlserver.User;
import com.peixeurbano.kraken.interfaces.UserDaoRemote;


@Stateless(name = "UserDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class UserDao implements UserDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(UserDao.class);
	
	@Override
	public User getUserByMail(final String mail){
		User user =new User();
		try {
			this.entityManager.clear();
			user = (User) this.entityManager.createNamedQuery(User.USER_BY_MAIL).setParameter("mail", mail).getSingleResult();
			return user;
		} catch (final PersistenceException e) {
            this.log.info(e.getMessage(), e);
            return user;
		}
			
	}
	
	@Override
	public String getMailscc() {
		
		this.entityManager.clear();
		List<String> mails = this.entityManager.createNamedQuery(User.USER_GET_MAILS_CC).getResultList();
		
		StringBuilder ccs = new StringBuilder(", ");
		for (String mail : mails) {
			ccs.append(mail+", ");
		}
		
		return ccs.substring(0, ccs.length()-2);
	}


}
