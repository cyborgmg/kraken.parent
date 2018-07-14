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
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;
import com.peixeurbano.kraken.interfaces.CampaignExcludeDaoRemote;
import com.peixeurbano.kraken.serializabes.AbstractParameters;

@Stateless(name = "CampaignExcludeDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class CampaignExcludeDao extends AbstractDao<Campaign, CampaignExclude> implements CampaignExcludeDaoRemote {
	
	EntityManager entityManager = Persistence.createEntityManagerFactory("kraken.sqlserver-pu").createEntityManager();

	@Override
	public AbstractParameters getAbstractParameters() {
		return new AbstractParameters(this.entityManager, CampaignExclude.CAMPAIGN_EXCLUDE_QUERY , null, CampaignExclude.CAMPAIGN_EXCLUDE_IN);
	}
	
	
	@Override
	public List<Campaign> getQuery(final String condition, final Integer campainId){
		try {
			
			StringBuilder qry = new StringBuilder(this.getAbstractParameters().getQry() +condition);
			
			qry.append(CampaignExclude.CAMPAIGN_EXCLUDE_NOT_IN+campainId+"\n");
			
			qry.append(CampaignExclude.CAMPAIGN_EXCLUDE_QUERY_NOT_IN+campainId+")");
			
			return this.getAbstractParameters().entityManager.createQuery(qry.toString()).setMaxResults(50).getResultList();
		} catch (final PersistenceException e) {
			e.printStackTrace();
            return new ArrayList<Campaign>(0);
		}			
	}
	
}
