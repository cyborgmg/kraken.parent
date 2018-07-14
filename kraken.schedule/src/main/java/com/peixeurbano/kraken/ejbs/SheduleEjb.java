package com.peixeurbano.kraken.ejbs;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.peixeurbano.kraken.bean.SheduleBean;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;

@Singleton
public class SheduleEjb implements SheduleEjbRemote {

	@Inject
	private SheduleBean sheduleBean;
	
	@Override
	public void init() {
		this.sheduleBean.init();
	}
	
	@Override
	public void processerJob() {
		this.sheduleBean.processerJob();
	}
	
	@Override
	public boolean scheduleCampain(final CampaignUseraccountType campaignUseraccountType){
		return this.sheduleBean.scheduleCampain(campaignUseraccountType);
	}
	
	@Override
	public void removeJobIds(final CampaignUseraccountType campaignUseraccountType) {
		this.sheduleBean.removeJobIds(campaignUseraccountType);
	}
	
	@Override
	public CampaignUseraccountType findAllCampaingId(final Integer campaignId) {
		return this.sheduleBean.findAllCampaingId(campaignId);
	}
	
	@Override
	public void removeExcludeJobIds(final CampaignUseraccountType campaignUseraccountType) {
		 this.sheduleBean.removeExcludeJobIds(campaignUseraccountType);
	}
	
	@Override
	public Integer getJobsSize() {
		return this.sheduleBean.getJobsSize();
	}

}
