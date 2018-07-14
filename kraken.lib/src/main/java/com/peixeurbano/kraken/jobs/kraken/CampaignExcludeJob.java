package com.peixeurbano.kraken.jobs.kraken;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.ScheduleDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;
import com.peixeurbano.kraken.utils.SendMail;
import com.peixeurbano.kraken.utils.funcoes;

@TransactionManagement(TransactionManagementType.BEAN)
public class CampaignExcludeJob implements InterruptableJob {

	private CampaignUseraccountDaoRemote campaignUseraccountDao;
	private ScheduleDaoRemote scheduleDao;
	public SheduleEjbRemote sheduleEjb;
	

	private CampaignUseraccountType campaignUseraccountType;	
	
	protected boolean interrupt=false;
	
	private Thread thread;

	public void stop() throws Exception{
		 
		 if(this.interrupt){
				throw new Exception("Job Interrompido");
		 }
		 
	 }

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {

		this.thread = Thread.currentThread();
		
		this.campaignUseraccountType = (CampaignUseraccountType) context.getJobDetail().getJobDataMap().get("campaignUseraccountType");
		
		try {

			this.stop();
			
			Context ctx = new InitialContext();
			this.scheduleDao= (ScheduleDaoRemote) ctx.lookup("java:global/kraken.sqlserver/ScheduleDao");
			this.scheduleDao.mergeCampaignGerando(this.campaignUseraccountType.getCampaign().getCampaignId(), Status.GERANDO, null);
			this.campaignUseraccountDao = (CampaignUseraccountDaoRemote) ctx.lookup("java:global/kraken.sqlserver/CampaignUseraccountDao");
			this.sheduleEjb= (SheduleEjbRemote) ctx.lookup("java:global/kraken.schedule/SheduleEjb");
	

			this.stop();
			
			this.campaignUseraccountType = this.campaignUseraccountDao.exclCampaigns(this.campaignUseraccountType);
			
			this.stop();
			
			if(!this.interrupt){
				this.scheduleDao.mergeCampaignDateFim(this.campaignUseraccountType.getCampaign().getCampaignId(),this.campaignUseraccountType.getDateini(), (new Timestamp((new Date()).getTime())),this.campaignUseraccountType.getSize().longValue(), this.campaignUseraccountType.getQry().toString());			
				SendMail.send(this.campaignUseraccountType.getMail(), "Termino com sucesso da campanha "+this.campaignUseraccountType.getCampaign().getName(), "A campanha "+this.campaignUseraccountType.getCampaign().getName()+", terminou com sucesso! E registrou "+this.campaignUseraccountType.getSize()+" e-mails.", this.campaignUseraccountType.getCc());			
			}				
			this.sheduleEjb.removeExcludeJobIds(this.campaignUseraccountType);
			
			this.stop();
			
		} catch (Exception e) {
			if(!this.interrupt){
				this.scheduleDao.mergeCampaignErroLog(this.campaignUseraccountType.getCampaign().getCampaignId(),funcoes.getStackTrace(e));
				SendMail.send(this.campaignUseraccountType.getMail(), "Termino com ERROS da campanha "+this.campaignUseraccountType.getCampaign().getName(), funcoes.getStackTrace(e), this.campaignUseraccountType.getCc());
				this.sheduleEjb.removeExcludeJobIds(this.campaignUseraccountType);
				e.printStackTrace();
			}else{
				this.campaignUseraccountDao.postCampaignUseraccounts("DELETE FROM dbo.CAMPAIGN_USERACCOUNT WHERE CAMPAIGN_ID = "+this.campaignUseraccountType.getCampaign().getCampaignId());
				this.scheduleDao.mergeCampaignGerando(this.campaignUseraccountType.getCampaign().getCampaignId(), Status.GERANDO, null);				
			}
			
		}	

	}
	
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		
		this.interrupt= this.sheduleEjb.findAllCampaingId(this.campaignUseraccountType.getCampaign().getCampaignId())!=null;
		   
	    if((this.interrupt)) {
		    this.thread.interrupt();
	    }

	}
	
}
