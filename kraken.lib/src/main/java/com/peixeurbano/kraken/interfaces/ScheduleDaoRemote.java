package com.peixeurbano.kraken.interfaces;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;

@Remote
public interface ScheduleDaoRemote {

	List<Campaign> getSchedules();

	void mergeCampaignErroLog(Integer campaignId, String erro);

	void mergeCampaignDateIni(Integer campaignId, Timestamp dateini, Long count, String qry);

	Campaign mergeCampaignGerando(Integer campaignId, Status status, String erro);

	void mergeCampaignDateFim(Integer campaignId, Timestamp dateini, Timestamp datefim, Long count, String qry);

}