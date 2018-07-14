package com.peixeurbano.kraken.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peixeurbano.kraken.entity.sqlserver.AgerangeSelect;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.DateSelect;
import com.peixeurbano.kraken.entity.sqlserver.MailinglistSelect;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.ValueSelect;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.CampainDaoRemote;
import com.peixeurbano.kraken.request.CampaignRequest;
import com.peixeurbano.kraken.utils.funcoes;


@Stateless(name = "CampainDao")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class CampainDao implements CampainDaoRemote {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(CampainDao.class);
	
	@EJB
	private CampaignUseraccountDaoRemote campaignUseraccountDao;
	
	@Override
	@Transactional
	public Campaign merge(Campaign campaign){
			
			try {
				
				campaign = this.campaignUseraccountDao.audite(campaign);
				
				campaign = this.entityManager.merge(campaign);
				
				return campaign;				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return campaign;
		
	}
	
	@Override
	@Transactional
	public Campaign merge(final Integer campaignId){
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);	
		
		return this.merge(campaign);		
	}
	
	@Override
	@Transactional
	public CampaignRequest percist(CampaignRequest campaignRequest){
		
				campaignRequest = this.preperCampaign(campaignRequest);
				campaignRequest.setCampaign( this.campaignUseraccountDao.audite(campaignRequest.getCampaign()));
				campaignRequest.setCampaign( this.entityManager.merge(campaignRequest.getCampaign()) );
	return campaignRequest;		
	}
	
	@Override
	public CampaignRequest preperCampaign(final CampaignRequest campaignRequest){
		
		/**VALORES*********************************************************************************************/
		
		List<ValueSelect> valueselects = funcoes.cloneList(campaignRequest.getCampaign().getValueSelects());
		for (ValueSelect valueselect : valueselects) {
			if(!valueselect.getCheck()){
				campaignRequest.getCampaign().getValueSelects().remove(valueselect);
			}
		}
		valueselects=null;
		
		/***DATAS**********************************************************************************************/
		
		List<DateSelect> dateSelects = funcoes.cloneList(campaignRequest.getCampaign().getDateSelects());
		for (DateSelect dateSelect : dateSelects) {
			
			int index = campaignRequest.getCampaign().getDateSelects().indexOf(dateSelect);
			
			switch (dateSelect.getSelectId()) {		
			
				case DATA_ANIVERSARIO:
					if(campaignRequest.getDataAniversario().getCheck()){
						campaignRequest.getCampaign().getDateSelects().set(index, campaignRequest.getDataAniversario().getInstace());
					}else{
						campaignRequest.getCampaign().getDateSelects().remove(index);
					}
					break;
				case DATA_CADASTRO:
					if(campaignRequest.getDataCadastro().getCheck()){
						campaignRequest.getCampaign().getDateSelects().set(index, campaignRequest.getDataCadastro().getInstace());
					}else{
						campaignRequest.getCampaign().getDateSelects().remove(index);
					}
					break;
				case DATA_ULTIMA_COMPRA:
					if(campaignRequest.getDataUltimaCompra().getCheck()){
						campaignRequest.getCampaign().getDateSelects().set(index, campaignRequest.getDataUltimaCompra().getInstace());
					}else{
						campaignRequest.getCampaign().getDateSelects().remove(index);
					}	
					break;
				case DATA_NAVIGATION:
					if(campaignRequest.getDataNavegacao().getCheck()){
						campaignRequest.getCampaign().getDateSelects().set(index, campaignRequest.getDataNavegacao().getInstace());
					}else{
						campaignRequest.getCampaign().getDateSelects().remove(index);
					}	
					break;
			}
			
		}
		dateSelects=null;
		
		/******************************************************************************************************/
		
		if(campaignRequest.getCampaign().getAgerangeSelects()!=null){
		for (AgerangeSelect agerangeselect : campaignRequest.getCampaign().getAgerangeSelects()) {
			if(agerangeselect.getAgeselId()<0){
				agerangeselect.setAgeselId(null);
			}	
		}}
		
		if(campaignRequest.getCampaign().getMailinglistSelects()!=null){
		for(MailinglistSelect mailinglistselect : campaignRequest.getCampaign().getMailinglistSelects()) {
			if(mailinglistselect.getMailId()<0){
				mailinglistselect.setMailId(null);
			}
		}}
			
		if(campaignRequest.getCampaign().getOffersSelects()!=null){
		for (OffersSelect offersselect : campaignRequest.getCampaign().getOffersSelects()) {
			if(offersselect.getOffersId()<0){
				offersselect.setOffersId(null);
			}
		}}
		
		if(campaignRequest.getCampaign().getOffersSelectNavs()!=null){
			for (OffersSelectNav offersSelectNav : campaignRequest.getCampaign().getOffersSelectNavs()) {
				if(offersSelectNav.getOffersSelectNavId()<0){
					offersSelectNav.setOffersSelectNavId(null);
				}
		}}
		
		if(campaignRequest.getCampaign().getCategorySelectNavs()!=null){
			for (CategorySelectNav categorySelectNav : campaignRequest.getCampaign().getCategorySelectNavs()) {
				if(categorySelectNav.getCategorySelectNavId()<0){
					categorySelectNav.setCategorySelectNavId(null);
				}
		}}
		
		if(campaignRequest.getCampaign().getCollectionsSelectNavs()!=null){
			for (CollectionsSelectNav collectionsSelectNav : campaignRequest.getCampaign().getCollectionsSelectNavs()) {
				if(collectionsSelectNav.getCollectionsSelectNavId()<0){
					collectionsSelectNav.setCollectionsSelectNavId(null);
				}
		}}
		
		if(campaignRequest.getCampaign().getAttributeSelectNavs()!=null){
			for (AttributeSelectNav attributeSelectNav : campaignRequest.getCampaign().getAttributeSelectNavs()) {
				if(attributeSelectNav.getAttributeSelectNavId()<0){
					attributeSelectNav.setAttributeSelectNavId(null);
				}
		}}		
		
		if(campaignRequest.getCampaign().getCampaignIdExcExcludes()!=null){
			for (CampaignExclude campaignExclude : campaignRequest.getCampaign().getCampaignIdExcExcludes()) {
				if(campaignExclude.getCampaignExcludeId()<0){
					campaignExclude.setCampaignExcludeId(null);
				}
		}}
		
	return campaignRequest;
	}
	
	@Override
	public List<Campaign> getAllList(){
		this.entityManager.clear();
		return this.entityManager.createNamedQuery(Campaign.CAMPAIGN_FINDALL_LIST).getResultList();
	}
	
	@Override
	public Campaign findById(final Integer campaignId){	
		
		 	this.entityManager.clear();
		 	try {
		 		if(campaignId!=null){
		 			return this.entityManager.find(Campaign.class, campaignId);
		 		}else{
		 			return null;
		 		}
			} catch (Exception e) {
				e.printStackTrace();
				return null;				
			} 			
	}
	
	@Override
	public void restoreCampains(){
		this.entityManager.createNativeQuery("update dbo.CAMPAIGN set ERRO_LOG=null , STATUS=3 WHERE STATUS=1").executeUpdate();
	}

}
