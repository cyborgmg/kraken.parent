package com.peixeurbano.kraken.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.peixeurbano.kraken.entity.abstracts.AbstractDao;
import com.peixeurbano.kraken.entity.redshift.kraken.UseraccountSubcategory;
import com.peixeurbano.kraken.entity.sqlserver.SubcategorySelect;
import com.peixeurbano.kraken.interfaces.UseraccountSubcategorysDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "UseraccountSubcategorysDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional

public class UseraccountSubcategorysDao extends AbstractDao<UseraccountSubcategory,SubcategorySelect> implements UseraccountSubcategorysDaoRemote{
	
	@Override
	public AbstractParameters getAbstractParameters() {
		
		return new AbstractParameters(Persistence.createEntityManagerFactory("kraken.redshift-pu").createEntityManager(), 
				UseraccountSubcategory.USERACCOUNTSUBCATEGORY_QUERY, null, UseraccountSubcategory.USERACCOUNTSUBCATEGORY_IN);
	}
	
}
