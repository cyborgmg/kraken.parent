package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.request.CampaignRequest;

@Remote
public interface CampainDaoRemote {

	void restoreCampains();

	Campaign findById(Integer campaignId);

	List<Campaign> getAllList();

	CampaignRequest preperCampaign(CampaignRequest campaignRequest);

	CampaignRequest percist(CampaignRequest campaignRequest);

	Campaign merge(Integer campaignId);

	Campaign merge(Campaign campaign);

}
