package com.peixeurbano.kraken.dao;

import java.sql.Timestamp;
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

import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.interfaces.ScheduleDaoRemote;

@Stateless(name = "ScheduleDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class ScheduleDao implements ScheduleDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(ScheduleDao.class);
	
	@Override
	public List<Campaign> getSchedules(){
		return this.entityManager.createNamedQuery(Campaign.SCHEDULE_FINDALL_LIST).getResultList();
	}
	
	@Override
	public void mergeCampaignErroLog(final Integer campaignId, final String erro){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		campaign.setStatus(Status.ERRO);
		campaign.setErroLog(erro);
		
		this.entityManager.merge(campaign);
		
	}
	
	@Override
	public void mergeCampaignDateIni(final Integer campaignId, final Timestamp dateini, final Long count, final String qry){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		campaign.setStatus(Status.GERANDO);
		
		campaign.setScheduleDateini(dateini);
		
		campaign.setScheduleCount(count);
		
		campaign.setQry(qry);
		
		this.entityManager.merge(campaign);
	}
	
	@Override
	public void mergeCampaignDateFim(final Integer campaignId, final Timestamp dateini, final Timestamp datefim, final Long count, final String qry){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		campaign.setScheduleDateini(dateini);
		
		campaign.setScheduleDatefim(datefim);
		
		campaign.setScheduleCount(count);
		
		campaign.setQry(qry);
		
		campaign.setStatus(Status.GERADA);
		
		this.entityManager.merge(campaign);
	}

	@Override
	public Campaign mergeCampaignGerando(final Integer campaignId, final Status status, final String erro ){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		campaign.setScheduleDateini(null);
		
		campaign.setScheduleDatefim(null);
		
		campaign.setScheduleCount(null);
		
		campaign.setQry(null);
		
		campaign.setErroLog(erro);		
		
		campaign.setStatus(status);
		
		return this.entityManager.merge(campaign);
	}

}
