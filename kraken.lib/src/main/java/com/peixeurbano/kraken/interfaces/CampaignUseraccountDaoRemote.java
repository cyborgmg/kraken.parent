package com.peixeurbano.kraken.interfaces;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.enums.BooleanEnum;
import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.request.CampaignRequest;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;

@Remote
public interface CampaignUseraccountDaoRemote {

	Throwable postCampaignUseraccounts(String qry);

	Campaign mergeCampaign(Integer campaignId, Status status, String erro);

	Campaign mergeCampaignEnable(Integer campaignId, BooleanEnum usable);

	CampaignUseraccountType exclCampaigns(CampaignUseraccountType campaignUseraccountType);

	long getUseraccountCount(CampaignRequest campaignrequest);

	StringBuilder getGenerateCampaignUseraccountsStringBuilder(Integer campaignId);	
	
	StringBuilder getGenerateCampaignUseraccountsStringBuilder(CampaignRequest campaignrequest);

	StringBuilder getGenerateCampaignUseraccountsStringBuilderCount(CampaignRequest campaignrequest);

	Campaign audite(Campaign campaign);

}