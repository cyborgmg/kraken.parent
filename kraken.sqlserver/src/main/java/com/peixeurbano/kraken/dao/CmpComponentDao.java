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

import com.peixeurbano.kraken.entity.imutable.ListCmpComponent;
import com.peixeurbano.kraken.entity.sqlserver.CmpComponentConfig;
import com.peixeurbano.kraken.interfaces.CmpComponentDaoRemote;


@Stateless(name = "CmpComponentDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class CmpComponentDao implements CmpComponentDaoRemote{
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(CmpComponentDao.class);
	
	@Override
	public List<CmpComponentConfig> getAll(){
		try {
			this.entityManager.clear();
			return this.entityManager.createNamedQuery(CmpComponentConfig.CMPCOMPONENTCONFIG_FINDALL).getResultList();			
		} catch (final PersistenceException e) {
            this.log.info(e.getMessage(), e);
            return new ArrayList<CmpComponentConfig>(0);
		}
			
	}

	@Override
	public ListCmpComponent populateParams(){
	
		ListCmpComponent listCmpComponent = new ListCmpComponent();
		
		for (CmpComponentConfig cmpComponentConfig : this.getAll()) {
			
			switch (cmpComponentConfig.getCmpEnum()) {
	    	
	         case HOURLOCK:  listCmpComponent.setHOURLOCK(cmpComponentConfig);;
	         		  break;	
	         case HOURUNLOCK: listCmpComponent.setHOURUNLOCK(cmpComponentConfig);
			  		  break;         
	         case MINUTELOCK: listCmpComponent.setMINUTELOCK(cmpComponentConfig);
	         		  break;
	         case MINUTEUNLOCK: listCmpComponent.setMINUTEUNLOCK(cmpComponentConfig);
    		  		  break;
	         case HOURRELOAD: listCmpComponent.setHOURRELOAD(cmpComponentConfig);
	         		  break;
	         case MINUTERELOAD: listCmpComponent.setMINUTERELOAD(cmpComponentConfig);
    		  		  break;		  
	    	}
		}
	return listCmpComponent;	
	}
	
}
