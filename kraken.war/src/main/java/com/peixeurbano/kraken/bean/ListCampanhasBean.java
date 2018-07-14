package com.peixeurbano.kraken.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;
import org.quartz.SchedulerException;

import com.peixeurbano.kraken.entity.enums.BooleanEnum;
import com.peixeurbano.kraken.entity.enums.Browser;
import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.CampainDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.interfaces.UseraccountDaoRemote;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;
import com.peixeurbano.kraken.utils.funcoes;

@ManagedBean
@Named("listCampanhasBean")
@ViewScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class ListCampanhasBean implements Serializable {

	private static final long serialVersionUID = -6764643250268625139L;
	
	public static final String parameterCampainId = "com.peixeurbano.kraken.bean.ListCampanhasBean.campainId";
	public static final String parameterCampainBk = "com.peixeurbano.kraken.bean.ListCampanhasBean.Campain.bk";
	
	@EJB(lookup = "java:global/kraken.sqlserver/CampainDao")
	private CampainDaoRemote campainDao;
	
	@EJB(lookup = "java:global/kraken.sqlserver/CampaignUseraccountDao")
	private CampaignUseraccountDaoRemote campaignUseraccountDao;
	
	@EJB(lookup = "java:global/kraken.redshift/UseraccountDao")
	private UseraccountDaoRemote useraccountDao;
	
	@EJB(lookup = "java:global/kraken.schedule/SheduleEjb")
	private SheduleEjbRemote sheduleEjb;
	
	@Inject
	private SessionBean sessionBean;
	
	@Inject
	private AplicationBean aplicationBean;
	
	private List<Campaign> campaigns = new ArrayList<Campaign>();	
	
	@PostConstruct
	public void init(){
	
		Campaign campaign = (Campaign) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(ListCampanhasBean.parameterCampainBk);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(ListCampanhasBean.parameterCampainBk);
		
		Boolean transaction=Boolean.TRUE;
		if(campaign!=null){
			transaction = (Boolean) funcoes.nvl(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(CampaignBean.TRANSACTION), Boolean.TRUE);		
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(CampaignBean.TRANSACTION);
		}
		
		if(!transaction){
			this.campainDao.merge(campaign);
		}
		
		this.setCampaigns( this.campainDao.getAllList() );
		
	}
	
	public void mergeCampaignEnable(final Campaign campaign){
		
		int size = this.getCampaigns().size();				
		for (int i = 0; i < size; i++) {
		
			if(this.getCampaigns().get(i).getCampaignId().intValue()==campaign.getCampaignId().intValue()){
				
				Campaign campaignlook=this.campaignUseraccountDao.mergeCampaignEnable(campaign.getCampaignId(), campaign.getUsable());
				if(campaignlook!=null){
					this.getCampaigns().set(i, campaignlook);						
					RequestContext.getCurrentInstance().update("Form1:dataTableCampanhas");
				}
				break;
			}
		}
		
	}
	
	public void setCampaignIdParameter(final Integer campaignId){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.getExternalContext().getSessionMap().remove(ListCampanhasBean.parameterCampainId);
		facesContext.getExternalContext().getSessionMap().put(ListCampanhasBean.parameterCampainId, campaignId);
		
		facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "campanha?faces-redirect=true");
		
	}
	
	public void generateCampaignUseraccounts(Campaign campaign) throws InterruptedException, NamingException, SchedulerException{
				
			campaign = this.campainDao.findById(campaign.getCampaignId());
		
			if(this.sheduleEjb.scheduleCampain(new CampaignUseraccountType(campaign, null, null, null, this.sessionBean.getEMail(), null))){
				
				this.sessionBean.setSize(this.sheduleEjb.getJobsSize());
				
				int size = this.getCampaigns().size();				
				for (int i = 0; i < size; i++) {
				
					if(this.getCampaigns().get(i).getCampaignId().intValue()==campaign.getCampaignId().intValue()){
						
						this.getCampaigns().set(i, this.campaignUseraccountDao.mergeCampaign(campaign.getCampaignId(), Status.GERANDO, null));						
						RequestContext.getCurrentInstance().update("Form1:dataTableCampanhas");
						break;
					}
				}					
				
			}
	}
	
	public void listener(){
		int lsize=this.aplicationBean.getSheduleEjbJobsSize();
		if( this.sessionBean.getSize()!=lsize ){
			this.update();
			this.sessionBean.setSize(lsize);
		}
	}
	
	private void update(){	
		this.setCampaigns( this.campainDao.getAllList() );
		RequestContext.getCurrentInstance().update("Form1:dataTableCampanhas");
		RequestContext.getCurrentInstance().update("Form1:Dlg2020:ButtonDlg2020");
	}	
	
	public String btnGenerateStyle(){
		
		if(this.sessionBean.getBrowserName()==Browser.CHROME){
			return "position: relative;height: 22px;bottom: -6px;";
		}else{
			return "position: relative;height: 22px;";
		}
		
	}

	public void processCampains() throws Exception{
		
		for (Campaign campaign : this.campaigns) {
			if(campaign.getUsable()==BooleanEnum.VERDADEIRO){
				this.generateCampaignUseraccounts(campaign);
				RequestContext.getCurrentInstance().update("Form1:Dlg2020:ButtonDlg2020 Form1:dataTableCampanhas");
			}
		}
		
	}
	
	/********GETS AND SETS**********************************************************************/	
	
	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(final List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
}
