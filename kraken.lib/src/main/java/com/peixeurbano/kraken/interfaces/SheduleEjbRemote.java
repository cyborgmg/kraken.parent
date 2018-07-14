package com.peixeurbano.kraken.interfaces;

import javax.ejb.Remote;

import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;

@Remote
public interface SheduleEjbRemote {

	boolean scheduleCampain(CampaignUseraccountType campaignUseraccountType);

	void removeJobIds(CampaignUseraccountType campaignUseraccountType);

	CampaignUseraccountType findAllCampaingId(Integer campaignId);

	void removeExcludeJobIds(CampaignUseraccountType campaignUseraccountType);

	void init();

	void processerJob();

	Integer getJobsSize();

}
