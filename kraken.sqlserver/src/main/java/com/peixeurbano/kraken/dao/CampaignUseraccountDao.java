package com.peixeurbano.kraken.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import com.peixeurbano.kraken.bean.SessionBean;
import com.peixeurbano.kraken.entity.enums.BooleanEnum;
import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;
import com.peixeurbano.kraken.entity.sqlserver.CampaignUseraccount;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.UserDaoRemote;
import com.peixeurbano.kraken.interfaces.UseraccountDaoRemote;
import com.peixeurbano.kraken.request.CampaignRequest;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;


@Stateless(name = "CampaignUseraccountDao")
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class CampaignUseraccountDao implements CampaignUseraccountDaoRemote  {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.sqlserver-pu");
	EntityManager entityManager = this.entityManagerFactory.createEntityManager();
	
	private final Log log = LogFactory.getLog(CampaignUseraccount.class);
	
	@EJB(lookup = "java:global/kraken.sqlserver/UserDao")
	private UserDaoRemote userDao;
	
	@Inject
	private SessionBean sessionBean;
	
	@EJB(lookup = "java:global/kraken.redshift/UseraccountDao")
	private UseraccountDaoRemote useraccountDao;	
	

	@Override
	public Campaign audite(final Campaign campaign){
		
		try {
			if(campaign.getCampaignId()==null){
				campaign.setUserIdInsert(this.sessionBean.getUser());
				campaign.setDateInsert(new Timestamp((new DateTime()).getMillis()));
			}
			campaign.setUserIdUpdate(this.sessionBean.getUser());
			campaign.setDateUpdate(new Timestamp((new DateTime()).getMillis()));
		} catch (Exception e) {
			campaign.setUserIdUpdate(this.userDao.getUserByMail("kraken@peixeurbano.com"));
			campaign.setDateUpdate(new Timestamp((new DateTime()).getMillis()));
		}
		
	return campaign;	
	}

	
	@Override
	public Campaign mergeCampaignEnable(final Integer campaignId, final BooleanEnum usable ){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		this.audite(campaign);
		
		campaign.setUsable(usable);
		
		return this.entityManager.merge(campaign);
	}
	
	@Override
	public Campaign mergeCampaign(final Integer campaignId, final Status status, final String erro ){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		this.audite(campaign);
		
		campaign.setScheduleDateini(null);
		
		campaign.setScheduleDatefim(null);
		
		campaign.setScheduleCount(null);
		
		campaign.setQry(null);
		
		campaign.setErroLog(erro);		
		
		campaign.setStatus(status);
		
		return this.entityManager.merge(campaign);
	}
	
	@Override
	public StringBuilder getGenerateCampaignUseraccountsStringBuilder(final Integer campaignId){
		
		this.entityManager.clear();
		
		Campaign campaign = this.entityManager.find(Campaign.class, campaignId);
		
		CampaignRequest campaignrequest = new CampaignRequest();
		
		campaign.setStatus(Status.GERANDO);
		
		campaignrequest.putCampaign(campaign);
		
		return this.getGenerateCampaignUseraccountsStringBuilder(campaignrequest);
	}
	
	@Override
	public StringBuilder getGenerateCampaignUseraccountsStringBuilderCount(final CampaignRequest campaignrequest){
		return this.getGenerateCampaignUseraccountsStringBuilder(campaignrequest, true);
	}
	
	@Override
	public StringBuilder getGenerateCampaignUseraccountsStringBuilder(final CampaignRequest campaignrequest){
		return this.getGenerateCampaignUseraccountsStringBuilder(campaignrequest, false);
	}
	
	
	private StringBuilder getGenerateCampaignUseraccountsStringBuilder(final CampaignRequest campaignrequest, final boolean count){
		
		/***CHECKAPP**************************/
		BooleanEnum checkApp = campaignrequest.getCampaign().getCheckApp()!=null?campaignrequest.getCampaign().getCheckApp():BooleanEnum.VERDADEIRO;
		/***CHECKVLPRESENTE**********************************/
		BooleanEnum checkvlpresente= campaignrequest.getCampaign().getCheckvlpresente()!=null?campaignrequest.getCampaign().getCheckvlpresente():BooleanEnum.VERDADEIRO;
		
		StringBuilder qry = new StringBuilder();
		
		qry.append("select "+(count?"count( distinct u.useraccountid )":"distinct u.useraccountid")+" \n");
		qry.append("from com.peixeurbano.kraken.entity.redshift.kraken.Useraccount AS u \n");
		/***CHECKAPP-CHECKVLPRESENTE-JOIN*****************************************/
		if(((campaignrequest.getCampaign().getCheckAppCheck())&(checkApp.getValor()==1))|
		   ((campaignrequest.getCampaign().getCheckvlpresenteCheck())&(checkvlpresente.getValor()==1))){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.SalesSource AS s \n");
		}
		/******SELECÃO DE TEXTO-JOIN************/
		if(campaignrequest.getCampaign().getTextSelectCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.Promotionalcode p \n");
		}	
		/******LISTA DE MAILS-JOIN**************/
		if(campaignrequest.getMailinglistSelectRequest().getCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MailinglistUseraccount m \n");
		}	
		/******LISTA DE OFERTAS*****************/
		if(campaignrequest.getOfferSelectRequest().getCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.UseraccountUnifieddiscount o \n");
		}
		/******LISTA DE NAVEGAÇÃO OFERTAS-CATEGORIA-COLECAO-ATRIBUTOS************/
		if((campaignrequest.getDataNavegacao().getCheck())||(campaignrequest.getOfferSelectNavRequest().getCheck())||
				(campaignrequest.getCategorySelectNavRequest().getCheck())||(campaignrequest.getAttributeSelectNavRequest().getCheck())||
				(campaignrequest.getCollectionsSelectNavRequest().getCheck())
				||(campaignrequest.getCategorySelectRequest().getCheck())){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.OcmSitenavigationclick os \n");
		}
		
		/******LISTA DE NAVEGAÇÃO OFERTAS************/		
		if(campaignrequest.getOfferSelectNavRequest().getCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatest mdl \n");
		}
		
		/******LISTA DE NAVEGAÇÃO CATEGORIA-COLECAO************/
		if((campaignrequest.getCategorySelectNavRequest().getCheck())||(campaignrequest.getCollectionsSelectNavRequest().getCheck())
				||(campaignrequest.getCategorySelectRequest().getCheck())){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngTag tag \n");
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatestTag mdlt \n");			
		}
		/******LISTA DE NAVEGAÇÃO ATRIBUTOS************/
		if(campaignrequest.getAttributeSelectNavRequest().getCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngAttribute matt \n");
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngAttributesOption matto \n");
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatestAttribute mdla \n");
		}
		/******SUB-CATEGORIA***************************/
		if(campaignrequest.getSubcategorySelectRequest().getCheck()){
			qry.append(" , com.peixeurbano.kraken.entity.redshift.kraken.UseraccountSubcategory subc \n");
		}
		
		qry.append("where 0=0 \n");
		
		/***CHECKAPP-CHECKVLPRESENTE**************************/
		if(((campaignrequest.getCampaign().getCheckAppCheck())&(checkApp.getValor()==1))|
		   ((campaignrequest.getCampaign().getCheckvlpresenteCheck())&(checkvlpresente.getValor()==1))){
			
			qry.append("and u.useraccountid=s.useraccountid \n");
			
		}
		/***CHECKVLPRESENTE**********************************/
		if((campaignrequest.getCampaign().getCheckvlpresenteCheck())&(checkvlpresente.getValor()==1)){
			
			qry.append("and s.sourceid = 2 \n");
			
		}		
		/******SELECÃO DE TEXTO************/
		if(campaignrequest.getCampaign().getTextSelectCheck()){
			qry.append("and  u.useraccountid=p.useraccountid and UPPER( p.code ) "+campaignrequest.getCampaign().getTextOperacao().getOperation(campaignrequest.getCampaign().getTextDesc())+" \n");
		}
		/******LISTA DE MAILS**************/
		if(campaignrequest.getMailinglistSelectRequest().getCheck()){
			qry.append("and u.useraccountid=m.useraccountid and m.mailinglistid in "+campaignrequest.getMailinglistSelectRequest().getSqlPart()+" \n");
		}
		
		/***FACEBOOK*****************************************/
		if(campaignrequest.getCampaign().getFacebookCheck()){
			qry.append("and u.usesfacebooklogin = "+campaignrequest.getCampaign().getFacebook().getValor()+" \n");
		}			
		/***CHECKCLIENT**************************************/
		if(campaignrequest.getCampaign().getCheckClientCheck()){
			qry.append("and u.datefirstpurchase is "+ (campaignrequest.getCampaign().getCheckClient().getValor()==0?"":"not") +" null \n");
		}	
		/***SEXO*********************************************/
		if(campaignrequest.getCampaign().getSexoCheck()){
			qry.append("and u.female = "+(campaignrequest.getCampaign().getSexo().getValor()=="M"?"0":"1")+" \n");
		}		
		/******ENGAJAMENTO*****************/
		if(campaignrequest.getCampaign().getEngagementCheck()){
			qry.append("and u.engagement in "+campaignrequest.getCampaign().engagementRestore().getEngagementSqlPart()+" \n");			
		}
		/******CATEGORIAS******************/
		if(campaignrequest.getCampaign().getCategoriesSelectCheck()){
			qry.append("and "+campaignrequest.getCampaign().getSqlPartCategoriesSelect()+" \n");
		}		
		/******SELECÃO DE DATAS************/
		if(campaignrequest.getDataCadastro().getCheck()){
			qry.append("and u.dateregistered "+campaignrequest.getDataCadastro().getSqlPart()+" \n");
		}	
		if(campaignrequest.getDataAniversario().getCheck()){
			qry.append("and u.anniversary "+campaignrequest.getDataAniversario().getSqlPart()+" \n");
		}	
		if(campaignrequest.getDataUltimaCompra().getCheck()){
			qry.append("and u.datelastpurchase "+campaignrequest.getDataUltimaCompra().getSqlPart()+" \n");
		}	
		
		/******SELECÃO DE VALORES**********/
		if(campaignrequest.getReceitaLiquidaCompras().getCheck()){
			qry.append("and u.netrevenue  "+campaignrequest.getReceitaLiquidaCompras().getSqlPart()+" \n");
		}	
		if(campaignrequest.getQuantidadeCompras().getCheck()){
			qry.append("and u.totalpurchases "+campaignrequest.getQuantidadeCompras().getSqlPart()+" \n");
		}
		if(campaignrequest.getMaiorValorCompra().getCheck()){
			qry.append("and u.maximumpurchasevalue "+campaignrequest.getMaiorValorCompra().getSqlPart()+" \n");
		}
		if(campaignrequest.getValorUtilizadoValePresente().getCheck()){
			qry.append("and u.countofvalepresente "+campaignrequest.getValorUtilizadoValePresente().getSqlPart()+" \n");
		}	
		/******LISTA DE IDADES*************/
		if(campaignrequest.getAgerangeSelectRequest().getCheck()){
			qry.append("and u.agerangeid in "+campaignrequest.getAgerangeSelectRequest().getSqlPart()+" \n");
		}	
		/******LISTA DE OFERTAS************/		
		if(campaignrequest.getOfferSelectRequest().getCheck()){
			qry.append(" and o.useraccountid=u.useraccountid and o.unifieddiscountid "+(campaignrequest.getCampaign().getOfferOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getOfferSelectRequest().getSqlPart());
		}
		/******LISTA DE NAVEGAÇÃO OFERTAS-CATEGORIA-COLECAO-ATRIBUTOS************/
		if((campaignrequest.getDataNavegacao().getCheck())||(campaignrequest.getOfferSelectNavRequest().getCheck())
				||(campaignrequest.getCategorySelectNavRequest().getCheck())
				||(campaignrequest.getAttributeSelectNavRequest().getCheck())
				||(campaignrequest.getCollectionsSelectNavRequest().getCheck())
				||(campaignrequest.getCategorySelectRequest().getCheck())){
			qry.append(" and os.useraccountid=u.useraccountid ");
		}
		/******LISTA DE NAVEGAÇÃO DATA************/
		if(campaignrequest.getDataNavegacao().getCheck()){
			qry.append(" and os.navigationdate "+campaignrequest.getDataNavegacao().getSqlPart()+" \n");
		}		
		/******LISTA DE NAVEGAÇÃO OFERTAS************/		
		if(campaignrequest.getOfferSelectNavRequest().getCheck()){
			qry.append(" and os.dealid = mdl.dealId");
			qry.append(" and mdl.dealNumericId "+(campaignrequest.getCampaign().getOfferNavOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getOfferSelectNavRequest().getSqlPart());
		}
		/******LISTA DE NAVEGAÇÃO CATEGORIA-COLECAO CATEGORIA************/
		if((campaignrequest.getCategorySelectNavRequest().getCheck())
				||(campaignrequest.getCollectionsSelectNavRequest().getCheck())
				||(campaignrequest.getCategorySelectRequest().getCheck())){
			
			qry.append(" and mdlt.dealId=os.dealid and tag.slug = mdlt.tagDescription");
			
			qry.append(" and mdlt.tagName in(");			
			String part = "";
			if((campaignrequest.getCategorySelectNavRequest().getCheck())||(campaignrequest.getCategorySelectRequest().getCheck())){
				part+="'tags',";
			}
			if(campaignrequest.getCollectionsSelectNavRequest().getCheck()){
				part+="'marketing',";
			}
			qry.append(part.substring(0, part.length()-1)+")");
		}
		/******LISTA DE CATEGORIA************/
		if(campaignrequest.getCategorySelectRequest().getCheck()){
			qry.append("\n and tag.id "+(campaignrequest.getCampaign().getCategoryOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getCategorySelectRequest().getSqlPart());
		}
		/******LISTA DE NAVEGAÇÃO CATEGORIA************/
		if(campaignrequest.getCategorySelectNavRequest().getCheck()){
			qry.append("\n and tag.id "+(campaignrequest.getCampaign().getCategoryNavOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getCategorySelectNavRequest().getSqlPart());
		}
		/******LISTA DE NAVEGAÇÃO COLECAO************/
		if(campaignrequest.getCollectionsSelectNavRequest().getCheck()){
			qry.append("\n and tag.id "+(campaignrequest.getCampaign().getCollectionNavOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getCollectionsSelectNavRequest().getSqlPart());
		}
		/******LISTA DE NAVEGAÇÃO ATRIBUTOS************/
		if(campaignrequest.getAttributeSelectNavRequest().getCheck()){
			
			qry.append(" and matt.id=matto.idAtt and matto.slug=mdla.attribute and mdla.dealId=os.dealid \n");
			
			qry.append("\n and matto.krakenId "+(campaignrequest.getCampaign().getAttributeNavOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getAttributeSelectNavRequest().getSqlPart());
		}
		/******SUB-CATEGORIA***************************/
		if(campaignrequest.getSubcategorySelectRequest().getCheck()){
			qry.append("\n and u.useraccountid=subc.useraccountid \n and subc.krakenid "+(campaignrequest.getCampaign().getSubcategoryOperacao().getValor()==0?"not":"")+" in \n"
					  +campaignrequest.getSubcategorySelectRequest().getSqlPart());
		}
		
		System.out.println(qry.toString());
		
		return qry;
		
	}

	@Override
	public Throwable postCampaignUseraccounts(final String qry) {
		this.entityManager.clear();
		try {
			Query query = this.entityManager.createNativeQuery(qry);
			query.executeUpdate();
			return null;
		} catch (Exception e) {						
			return e;
		}			 	
	}
	
	@Override
	public CampaignUseraccountType exclCampaigns(final CampaignUseraccountType campaignUseraccountType){
			
			this.entityManager.clear();
			
			List<CampaignExclude> campaignExcludes = this.entityManager.createQuery("SELECT e FROM CampaignExclude e WHERE e.campaign.campaignId = :campaignId ").setParameter("campaignId", campaignUseraccountType.getCampaign().getCampaignId()).getResultList(); 
			
			for (CampaignExclude campaignExclude : campaignExcludes) {
				
				StringBuilder qry = new StringBuilder();
				qry.append("DELETE from CampaignUseraccount c \n"); 
				qry.append("WHERE \n");
				qry.append("c.campaign.campaignId= "+campaignExclude.getCampaign().getCampaignId()+" and \n");
				qry.append("c.useraccountId IN \n");
				qry.append("( \n");
				qry.append("SELECT e.useraccountId from CampaignUseraccount e, Campaign c \n");
				qry.append("WHERE \n");
  				qry.append("c.campaignId = e.campaign.campaignId and \n");
  				qry.append("c.usable=1 and \n");
  				qry.append("e.campaign.campaignId="+campaignExclude.getCampaignIdExc()+" \n");
				qry.append(") \n");
				Query query = this.entityManager.createQuery(qry.toString());
				query.executeUpdate();
			}

			
			
			this.entityManager.clear();
			
			campaignUseraccountType.setSize( ((Long) this.entityManager.createQuery("SELECT count(c) FROM CampaignUseraccount c WHERE c.campaign.campaignId = :campaignId ").setParameter("campaignId", campaignUseraccountType.getCampaign().getCampaignId()).getSingleResult()).intValue() ); 
			
			return campaignUseraccountType;
	}
	
	@Override
	public long getUseraccountCount(final CampaignRequest campaignrequest) {
		
		this.entityManager.clear();
		
		List<Integer> useraccounts = this.useraccountDao.getUseraccounts(this.getGenerateCampaignUseraccountsStringBuilder(campaignrequest));
		
		if(campaignrequest.getCampaignExcludeRequest().getTargets().size()>0){
		
			List<Integer> campaignUseraccounts = new ArrayList<Integer>(); 
			
			for (CampaignExclude campaignExclude : campaignrequest.getCampaignExcludeRequest().getTargets()) {
			
				for (Integer useraccountId : useraccounts) {
					
					if((Integer) this.entityManager.createNamedQuery(CampaignUseraccount.CAMPAIGN_USERACCOUNT_COUNT_BY_CAMPAIN_EXCLUDE_ID_AND_USERACCOUNTID)
							.setParameter("campaignId", campaignExclude.getCampaignIdExc()).setParameter("useraccountId", useraccountId).getSingleResult()>0){
					
						campaignUseraccounts.add( useraccountId );
					
					}
					
				}
				
			}
			
			useraccounts.removeAll(campaignUseraccounts);
		}	
		
	return useraccounts.size();
	}
	
}
