package com.peixeurbano.kraken.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import com.peixeurbano.kraken.entity.dao.SqlserverDao;
import com.peixeurbano.kraken.entity.redshift.kraken.MngAttributesOption;
import com.peixeurbano.kraken.entity.redshift.kraken.MngDealsLatest;
import com.peixeurbano.kraken.entity.redshift.kraken.MngTag;
import com.peixeurbano.kraken.entity.redshift.kraken.Unifieddiscount;
import com.peixeurbano.kraken.entity.redshift.kraken.UseraccountSubcategory;
import com.peixeurbano.kraken.entity.sqlserver.AgerangeSelect;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelect;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.MailinglistSelect;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelect;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;
import com.peixeurbano.kraken.entity.sqlserver.SubcategorySelect;
import com.peixeurbano.kraken.interfaces.CampaignExcludeDaoRemote;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.CampainDaoRemote;
import com.peixeurbano.kraken.interfaces.MngAttributesOptionDaoRemote;
import com.peixeurbano.kraken.interfaces.MngDealsLatestDaoRemote;
import com.peixeurbano.kraken.interfaces.MngTagDaoRemote;
import com.peixeurbano.kraken.interfaces.MngTagMarketingDaoRemote;
import com.peixeurbano.kraken.interfaces.MngTagSelectDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.interfaces.UnifieddiscountDaoRemote;
import com.peixeurbano.kraken.interfaces.UseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.UseraccountSubcategorysDaoRemote;
import com.peixeurbano.kraken.request.CampaignRequest;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;
import com.peixeurbano.kraken.utils.funcoes;

@ManagedBean
@Named("campaignBean")
@ViewScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class CampaignBean implements Serializable {

	private static final long serialVersionUID = -4762498802862399669L;
	
	public static final String TRANSACTION = "com.peixeurbano.kraken.bean.CampaignBean.Transaction";
	
	@EJB(lookup = "java:global/kraken.schedule/SheduleEjb")
	private SheduleEjbRemote sheduleEjb;	

	@EJB(lookup = "java:global/kraken.redshift/UnifieddiscountDao")
	private UnifieddiscountDaoRemote unifieddiscountDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MngDealsLatestDao")
	private MngDealsLatestDaoRemote mngDealsLatestDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MngTagDao")
	private MngTagDaoRemote mngTagDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MngTagSelectDao")
	private MngTagSelectDaoRemote mngTagSelectDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MngTagMarketingDao")
	private MngTagMarketingDaoRemote mngTagMarketingDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MngAttributesOptionDao")
	private MngAttributesOptionDaoRemote mngAttributesOptionDao;
	
	@EJB(lookup = "java:global/kraken.redshift/UseraccountDao")
	private UseraccountDaoRemote useraccountDao;	
	
	@EJB(lookup = "java:global/kraken.redshift/UseraccountSubcategorysDao")
	private UseraccountSubcategorysDaoRemote useraccountSubcategorysDao;	

	@EJB(lookup = "java:global/kraken.sqlserver/CampainDao")
	private CampainDaoRemote campainDao;	
	
	@EJB(lookup = "java:global/kraken.sqlserver/CampaignUseraccountDao")
	private CampaignUseraccountDaoRemote campaignUseraccountDao;

	@EJB(lookup = "java:global/kraken.sqlserver/CampaignExcludeDao")
	private CampaignExcludeDaoRemote campaignExcludeDao;
	
	@Inject
	private AplicationBean aplicationBean;

	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private SqlserverDao sqlserverDao;
	
	private CampaignRequest campaignRequest;
	
	private Campaign campaign = new Campaign();
	
	@PostConstruct
	public void init(){
		
		this.campaignRequest = new CampaignRequest();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if(facesContext.getExternalContext().getSessionMap().get(ListCampanhasBean.parameterCampainId)!=null){		
			this.setCampaign( this.sqlserverDao.getEntityManager().find(Campaign.class, ( (Integer) facesContext.getExternalContext().getSessionMap().get(ListCampanhasBean.parameterCampainId) ) ));
		}else{
			this.setCampaign( new Campaign() );
		}
		
		facesContext.getExternalContext().getSessionMap().remove(ListCampanhasBean.parameterCampainBk);
		facesContext.getExternalContext().getSessionMap().put(ListCampanhasBean.parameterCampainBk, funcoes.clone(this.getCampaign()));
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(ListCampanhasBean.parameterCampainId);
		
		this.restore();

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(CampaignBean.TRANSACTION);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CampaignBean.TRANSACTION,Boolean.FALSE);
		
	}
	
	/********OFERTAS*****************************************************/
	public void getUnifieddiscount(final ActionEvent ae){
		
		this.campaignRequest.getOfferSelectRequest().setSources(this.unifieddiscountDao.getQuery(this.campaignRequest.getOfferSelectRequest().getOperationText().getOperation(this.campaignRequest.getOfferSelectRequest().getText()), this.campaignRequest.getStateOffer())  );
	}
	
	public void addOffersSelects(final ActionEvent ae) {
		
		List<Unifieddiscount> UnifieddiscountClone = funcoes.cloneList(this.campaignRequest.getOfferSelectRequest().getSources());
		
		for (Unifieddiscount unifieddiscount : UnifieddiscountClone) {
			if(unifieddiscount.getCheck()){
				OffersSelect offersSelect = new OffersSelect( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), unifieddiscount.getId(), unifieddiscount.getValue() );
				this.campaignRequest.getOfferSelectRequest().getTargets().add(offersSelect);
				this.campaignRequest.getOfferSelectRequest().setTarget(offersSelect);
			}	
		}
		
		for (Unifieddiscount unifieddiscount : UnifieddiscountClone) {
			if(unifieddiscount.getCheck()){
				this.campaignRequest.getOfferSelectRequest().getSources().remove(unifieddiscount);
			}	
		}
		
	}
	
	public void removeOffersSelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getOfferSelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getOfferSelectRequest().getTargets().remove(this.campaignRequest.getOfferSelectRequest().getTarget());
		}
		
		if(!this.campaignRequest.getOfferSelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getOfferSelectRequest().setTarget(this.campaignRequest.getOfferSelectRequest().getTargets().get(0));
		}
		
	}	
	
	/********CATEGORY*****************************************************/
	public void getMngTagCategoryQuery(final ActionEvent ae){
		
		this.campaignRequest.getCategorySelectRequest().setSources(this.mngTagDao.getQuery(this.campaignRequest.getCategorySelectRequest().getOperationText().getOperation(this.campaignRequest.getCategorySelectRequest().getText())) );
	}
	
	public void addCategorySelects(final ActionEvent ae) {
		
		List<MngTag> mngTagsClone = funcoes.cloneList(this.campaignRequest.getCategorySelectRequest().getSources());
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				CategorySelect categorySelect = new CategorySelect( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), mngTag.getId(), mngTag.getValue() );
				this.campaignRequest.getCategorySelectRequest().getTargets().add(categorySelect);
				this.campaignRequest.getCategorySelectRequest().setTarget(categorySelect);
			}	
		}
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				this.campaignRequest.getCategorySelectRequest().getSources().remove(mngTag);
			}	
		}
		
	}
	
	public void removeCategorySelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getCategorySelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getCategorySelectRequest().getTargets().remove(this.campaignRequest.getCategorySelectRequest().getTarget());
		}
		
		if(!this.campaignRequest.getCategorySelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getCategorySelectRequest().setTarget(this.campaignRequest.getCategorySelectRequest().getTargets().get(0));
		}
		
	}
	
	/********SUBCATEGORY*****************************************************/
	public void getuseraccountSubcategory(final ActionEvent ae){
		
		this.campaignRequest.getSubcategorySelectRequest().setSources(this.useraccountSubcategorysDao.getQuery(this.campaignRequest.getSubcategorySelectRequest().getOperationText().getOperation(this.campaignRequest.getSubcategorySelectRequest().getText())) );
	}
	
	public void addSubcategorySelects(final ActionEvent ae) {
		
		List<UseraccountSubcategory> useraccountSubcategorysClone = funcoes.cloneList(this.campaignRequest.getSubcategorySelectRequest().getSources());
		
		for (UseraccountSubcategory useraccountSubcategory : useraccountSubcategorysClone) {
			if(useraccountSubcategory.getCheck()){
				SubcategorySelect subcategorySelect = new SubcategorySelect( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), useraccountSubcategory.getId(), useraccountSubcategory.getValue() );
				this.campaignRequest.getSubcategorySelectRequest().getTargets().add(subcategorySelect);
				this.campaignRequest.getSubcategorySelectRequest().setTarget(subcategorySelect);
			}	
		}
		
		for (UseraccountSubcategory useraccountSubcategory : useraccountSubcategorysClone) {
			if(useraccountSubcategory.getCheck()){
				this.campaignRequest.getSubcategorySelectRequest().getSources().remove(useraccountSubcategory);
			}	
		}
		
	}
	
	public void removeSubcategorySelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getSubcategorySelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getSubcategorySelectRequest().getTargets().remove(this.campaignRequest.getSubcategorySelectRequest().getTarget());
		}
		
		if(!this.campaignRequest.getSubcategorySelectRequest().getTargets().isEmpty()){
			this.campaignRequest.getSubcategorySelectRequest().setTarget(this.campaignRequest.getSubcategorySelectRequest().getTargets().get(0));
		}
		
	}
	
	/********CAMPAIN-EXCLUDE*****************************************************/
	public void getCampainQuery(final ActionEvent ae){
		
		this.campaignRequest.getCampaignExcludeRequest().setSources(this.campaignExcludeDao.getQuery(this.campaignRequest.getCampaignExcludeRequest().getOperationText().getOperation(this.campaignRequest.getCampaignExcludeRequest().getText()), this.campaignRequest.getCampaign().getCampaignId() ) );
	}
	
	public void addCampainExclude(final ActionEvent ae) {
		
		List<Campaign> campaignClone = funcoes.cloneList(this.campaignRequest.getCampaignExcludeRequest().getSources());
		
		for (Campaign campaign : campaignClone) {
			if(campaign.getCheck()){
				CampaignExclude campaignExclude = new CampaignExclude( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), campaign.getId(), campaign.getValue() );
				this.campaignRequest.getCampaignExcludeRequest().getTargets().add(campaignExclude);
				this.campaignRequest.getCampaignExcludeRequest().setTarget(campaignExclude);
			}	
		}
		
		for (Campaign campaign : campaignClone) {
			if(campaign.getCheck()){
				this.campaignRequest.getCampaignExcludeRequest().getSources().remove(campaign);
			}	
		}
		
	}
	
	public void removeCampainExclude(final ActionEvent ae){
		
		if(!this.campaignRequest.getCampaignExcludeRequest().getTargets().isEmpty()){
			this.campaignRequest.getCampaignExcludeRequest().getTargets().remove(this.campaignRequest.getCampaignExcludeRequest().getTarget());
		}
		
		if(!this.campaignRequest.getCampaignExcludeRequest().getTargets().isEmpty()){
			this.campaignRequest.getCampaignExcludeRequest().setTarget(this.campaignRequest.getCampaignExcludeRequest().getTargets().get(0));
		}
		
	}
	
	/********OFERTAS-NAVG*****************************************************/
	public void getMngDealsLatestQuery(final ActionEvent ae){
		
		this.campaignRequest.getOfferSelectNavRequest().setSources(this.mngDealsLatestDao.getQuery(this.campaignRequest.getOfferSelectNavRequest().getOperationText().getOperation(this.campaignRequest.getOfferSelectNavRequest().getText()), this.campaignRequest.getStateOffer())  );
	}
	
	public void addOffersNavSelects(final ActionEvent ae) {
		
		List<MngDealsLatest> mngDealsLatestsClone = funcoes.cloneList(this.campaignRequest.getOfferSelectNavRequest().getSources());
		
		for (MngDealsLatest mngdealslatest : mngDealsLatestsClone) {
			if(mngdealslatest.getCheck()){
				OffersSelectNav offersSelectNav = new OffersSelectNav( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), mngdealslatest.getId(), mngdealslatest.getValue() );
				this.campaignRequest.getOfferSelectNavRequest().getTargets().add(offersSelectNav);
				this.campaignRequest.getOfferSelectNavRequest().setTarget(offersSelectNav);
			}	
		}
		
		for (MngDealsLatest mngDealsLatest : mngDealsLatestsClone) {
			if(mngDealsLatest.getCheck()){
				this.campaignRequest.getOfferSelectNavRequest().getSources().remove(mngDealsLatest);
			}	
		}
		
	}
	
	public void removeOffersNavSelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getOfferSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getOfferSelectNavRequest().getTargets().remove(this.campaignRequest.getOfferSelectNavRequest().getTarget());
		}
		
		if(!this.campaignRequest.getOfferSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getOfferSelectNavRequest().setTarget(this.campaignRequest.getOfferSelectNavRequest().getTargets().get(0));
		}
		
	}	
	
	/********CATEGORY-NAVG*****************************************************/
	public void getMngTagQuery(final ActionEvent ae){
		
		this.campaignRequest.getCategorySelectNavRequest().setSources(this.mngTagDao.getQuery(this.campaignRequest.getCategorySelectNavRequest().getOperationText().getOperation(this.campaignRequest.getCategorySelectNavRequest().getText())) );
	}
	
	public void addCategorySelectNavs(final ActionEvent ae) {
		
		List<MngTag> mngTagsClone = funcoes.cloneList(this.campaignRequest.getCategorySelectNavRequest().getSources());
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				CategorySelectNav categorySelectNav = new CategorySelectNav( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), mngTag.getId(), mngTag.getValue() );
				this.campaignRequest.getCategorySelectNavRequest().getTargets().add(categorySelectNav);
				this.campaignRequest.getCategorySelectNavRequest().setTarget(categorySelectNav);
			}	
		}
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				this.campaignRequest.getCategorySelectNavRequest().getSources().remove(mngTag);
			}	
		}
		
	}
	
	public void removeCategorySelectNav(final ActionEvent ae){
		
		if(!this.campaignRequest.getCategorySelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getCategorySelectNavRequest().getTargets().remove(this.campaignRequest.getCategorySelectNavRequest().getTarget());
		}
		
		if(!this.campaignRequest.getCategorySelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getCategorySelectNavRequest().setTarget(this.campaignRequest.getCategorySelectNavRequest().getTargets().get(0));
		}
		
	}		

	/********COLLECTION-NAVG*****************************************************/
	public void getMngTagMarketingQuery(final ActionEvent ae){
		
		this.campaignRequest.getCollectionsSelectNavRequest().setSources(this.mngTagMarketingDao.getQuery(this.campaignRequest.getCollectionsSelectNavRequest().getOperationText().getOperation(this.campaignRequest.getCollectionsSelectNavRequest().getText())) );
	}
	
	public void addCollectionSelectNavs(final ActionEvent ae) {
		
		List<MngTag> mngTagsClone = funcoes.cloneList(this.campaignRequest.getCollectionsSelectNavRequest().getSources());
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				CollectionsSelectNav collectionsSelectNav = new CollectionsSelectNav( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), mngTag.getId(), mngTag.getValue() );
				this.campaignRequest.getCollectionsSelectNavRequest().getTargets().add(collectionsSelectNav);
				this.campaignRequest.getCollectionsSelectNavRequest().setTarget(collectionsSelectNav);
			}	
		}
		
		for (MngTag mngTag : mngTagsClone) {
			if(mngTag.getCheck()){
				this.campaignRequest.getCollectionsSelectNavRequest().getSources().remove(mngTag);
			}	
		}
		
	}
	
	public void removeCollectionSelectNav(final ActionEvent ae){
		
		if(!this.campaignRequest.getCollectionsSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getCollectionsSelectNavRequest().getTargets().remove(this.campaignRequest.getCollectionsSelectNavRequest().getTarget());
		}
		
		if(!this.campaignRequest.getCollectionsSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getCollectionsSelectNavRequest().setTarget(this.campaignRequest.getCollectionsSelectNavRequest().getTargets().get(0));
		}
		
	}	
	
	/********ATRIBUTOS-NAVG*****************************************************/
	public void getMngAttributeQuery(final ActionEvent ae){
		
		this.campaignRequest.getAttributeSelectNavRequest().setSources(this.mngAttributesOptionDao.getQuery(this.campaignRequest.getAttributeSelectNavRequest().getOperationText().getOperation(this.campaignRequest.getAttributeSelectNavRequest().getText())) );
	}
	
	public void addAttributeSelectNavs(final ActionEvent ae) {
		
		List<MngAttributesOption> mngAttributesOptionsClone = funcoes.cloneList(this.campaignRequest.getAttributeSelectNavRequest().getSources());
		
		for (MngAttributesOption mngAttributesOption : mngAttributesOptionsClone) {
			if(mngAttributesOption.getCheck()){
				AttributeSelectNav attributeSelectNav = new AttributeSelectNav( (int) (Math.round((Math.random()*10000))*(-1)), this.getCampaign(), mngAttributesOption.getId(), mngAttributesOption.getValue() );
				this.campaignRequest.getAttributeSelectNavRequest().getTargets().add(attributeSelectNav);
				this.campaignRequest.getAttributeSelectNavRequest().setTarget(attributeSelectNav);
			}	
		}
		
		for (MngAttributesOption mngAttributesOption : mngAttributesOptionsClone) {
			if(mngAttributesOption.getCheck()){
				this.campaignRequest.getAttributeSelectNavRequest().getSources().remove(mngAttributesOption);
			}	
		}
		
	}
	
	public void removeAttributeSelectNav(final ActionEvent ae){
		
		if(!this.campaignRequest.getAttributeSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getAttributeSelectNavRequest().getTargets().remove(this.campaignRequest.getAttributeSelectNavRequest().getTarget());
		}
		
		if(!this.campaignRequest.getAttributeSelectNavRequest().getTargets().isEmpty()){
			this.campaignRequest.getAttributeSelectNavRequest().setTarget(this.campaignRequest.getAttributeSelectNavRequest().getTargets().get(0));
		}
		
	}	
	
	/*****LISTA DE MAILS********************************************************/
	
	public void addMailinglistSelect(final ActionEvent ae){
		
		for (MailinglistSelect mailinglistselect : this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects()) {
			if(mailinglistselect.getMailinglistId()==this.campaignRequest.getMailinglistSelectRequest().getMailinglist().getMailinglistid() ){
				return;
			}
		}
		
		MailinglistSelect mailinglistselect = new MailinglistSelect( (this.campaignRequest.getMailinglistSelectRequest().getMailinglist().getMailinglistid()*(-1)), this.campaignRequest.getCampaign(), this.campaignRequest.getMailinglistSelectRequest().getMailinglist().getMailinglistid(),this.campaignRequest.getMailinglistSelectRequest().getMailinglist().getMailinglistname());
		this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects().add( mailinglistselect );
		this.campaignRequest.getMailinglistSelectRequest().setMailinglistSelect(mailinglistselect);
		
	}
	
	public void removeMailinglistSelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects().isEmpty()){
			this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects().remove(this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelect());
		}
		
		if(!this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects().isEmpty()){
			this.campaignRequest.getMailinglistSelectRequest().setMailinglistSelect(this.campaignRequest.getMailinglistSelectRequest().getMailinglistSelects().get(0));
		}
	}

	/*****LISTA DE IDADES********************************************************/
	
	public void addAgerangeSelect(final ActionEvent ae){
		
		
		for (AgerangeSelect agerangeselect : this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects()) {
			if( agerangeselect.getAgerange().getAgeId()==this.campaignRequest.getAgerangeSelectRequest().getAgerange().getAgeId() ){
				return;
			}
		}
		AgerangeSelect agerangeselect = new AgerangeSelect((this.campaignRequest.getAgerangeSelectRequest().getAgerange().getAgeId()*(-1)), this.campaignRequest.getAgerangeSelectRequest().getAgerange(), this.campaignRequest.getCampaign());
		this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects().add( agerangeselect );
		this.campaignRequest.getAgerangeSelectRequest().setAgerangeSelect(agerangeselect);
		
	}
	
	public void removeAgerangeSelect(final ActionEvent ae){
		
		if(!this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects().isEmpty()){
			this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects().remove(this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelect());
		}
		
		if(!this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects().isEmpty()){
			this.campaignRequest.getAgerangeSelectRequest().setAgerangeSelect(this.campaignRequest.getAgerangeSelectRequest().getAgerangeSelects().get(0));
		}
	}
	
	public void mergeCampaign(final ActionEvent ae) throws PersistenceException, NamingException{

		Timestamp dateini =(new Timestamp((new Date()).getTime()));

		try {
			
			this.campaignRequest = this.campainDao.percist(this.campaignRequest); 
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			
			facesContext.getExternalContext().getSessionMap().remove(ListCampanhasBean.parameterCampainId);
			facesContext.getExternalContext().getSessionMap().put(ListCampanhasBean.parameterCampainId, this.campaignRequest.getCampaign().getCampaignId());
			
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(CampaignBean.TRANSACTION);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CampaignBean.TRANSACTION,Boolean.TRUE);
			
			
			facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "campanha");

			StringBuilder msg = new StringBuilder("Campanha Salva com sucesso!!! E está em processamento neste momento.");

			this.sheduleEjb.scheduleCampain(new CampaignUseraccountType(this.campaignRequest.getCampaign(), null, null, null, this.sessionBean.getEMail(), null));

			Timestamp datefim =(new Timestamp((new Date()).getTime()));
			
			msg.append("\n Sua validação durou "+((datefim.getTime()-dateini.getTime())/1000)+" segundos.");
			
		    facesContext.addMessage("growlInfo", new FacesMessage(FacesMessage.SEVERITY_INFO,"Infomação",msg.toString()));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(CampaignBean.TRANSACTION);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(CampaignBean.TRANSACTION,Boolean.FALSE);					
			FacesContext.getCurrentInstance().addMessage("growlErro", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Sua Campanha retornou ERROS!!"));
			e.printStackTrace();
		}

	}
	
	/***************************************************************************/
	
	public void restore(){
	
		this.getCampaign().setCampaignCheck(Boolean.FALSE);
		
		this.campaignRequest.putCampaign(this.getCampaign());
		
		if(this.campaignRequest.getOfferSelectRequest().getCheck()){
			this.campaignRequest.getOfferSelectRequest().setSourcenames(this.unifieddiscountDao.getSourceNames(this.campaignRequest.getOfferSelectRequest().getTargets()));
		}
		if(this.campaignRequest.getCategorySelectRequest().getCheck()){
			this.campaignRequest.getCategorySelectRequest().setSourcenames(this.mngTagSelectDao.getSourceNames(this.campaignRequest.getCategorySelectRequest().getTargets()));
		}
		if(this.campaignRequest.getSubcategorySelectRequest().getCheck()){
			this.campaignRequest.getSubcategorySelectRequest().setSourcenames(this.useraccountSubcategorysDao.getSourceNames(this.campaignRequest.getSubcategorySelectRequest().getTargets()));
		}
		if(this.campaignRequest.getCampaignExcludeRequest().getCheck()){
			this.campaignRequest.getCampaignExcludeRequest().setSourcenames(this.campaignExcludeDao.getSourceNames(this.campaignRequest.getCampaignExcludeRequest().getTargets()));
		}
		if(this.campaignRequest.getOfferSelectNavRequest().getCheck()){
			this.campaignRequest.getOfferSelectNavRequest().setSourcenames(this.mngDealsLatestDao.getSourceNames(this.campaignRequest.getOfferSelectNavRequest().getTargets()));
		}
		if(this.campaignRequest.getCategorySelectNavRequest().getCheck()){
			this.campaignRequest.getCategorySelectNavRequest().setSourcenames(this.mngTagDao.getSourceNames(this.campaignRequest.getCategorySelectNavRequest().getTargets()));
		}
		if(this.campaignRequest.getCollectionsSelectNavRequest().getCheck()){
			this.campaignRequest.getCollectionsSelectNavRequest().setSourcenames(this.mngTagMarketingDao.getSourceNames(this.campaignRequest.getCollectionsSelectNavRequest().getTargets()));
		}
		if(this.campaignRequest.getAttributeSelectNavRequest().getCheck()){
			this.campaignRequest.getAttributeSelectNavRequest().setSourcenames(this.mngAttributesOptionDao.getSourceNames(this.campaignRequest.getAttributeSelectNavRequest().getTargets()));
		}
		
		//this.getCampaignExcludeMethod().restore();
		
		this.campaignRequest.getMailinglistSelectRequest().setMailinglistnames(this.aplicationBean.getMailinglists());		
		this.campaignRequest.getMailinglistSelectRequest().setMailinglists(this.aplicationBean.getMailinglists());		
		this.campaignRequest.getAgerangeSelectRequest().setAgeranges(this.aplicationBean.getAgeranges());
		
		this.campaignRequest.setStateOffer(this.aplicationBean.getStates().get(0));
		this.campaignRequest.setStateOfferNav(this.aplicationBean.getStates().get(0));
	}
/*
	public SelectMethod<Campaign, CampaignExclude> getCampaignExcludeMethod() {
		return this.campaignExcludeMethod;
	}
*/
	/********GETS AND SETS**********************************************************************/
	public CampaignRequest getCampaignRequest() {
		return this.campaignRequest;
	}

	public void setCampaignRequest(final CampaignRequest campaignRequest) {
		this.campaignRequest = campaignRequest;
	}

	private Campaign getCampaign() {
		return this.campaign;
	}

	private void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}
}
