package com.peixeurbano.kraken.jobs.kraken;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.sqlserver.CampaignUseraccount;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.ScheduleDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.interfaces.UseraccountDaoRemote;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;
import com.peixeurbano.kraken.utils.SendMail;
import com.peixeurbano.kraken.utils.funcoes;

@TransactionManagement(TransactionManagementType.BEAN)
public class CampaignJob implements InterruptableJob {
	
	public CampaignUseraccountDaoRemote campaignUseraccountDao;
	
	private UseraccountDaoRemote useraccountDao;

	public ScheduleDaoRemote scheduleDao;
	
	public SheduleEjbRemote sheduleEjb;
		
	protected CampaignUseraccountType campaignUseraccountType;
	
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
			this.campaignUseraccountDao= (CampaignUseraccountDaoRemote) ctx.lookup("java:global/kraken.sqlserver/CampaignUseraccountDao");	
			this.useraccountDao = (UseraccountDaoRemote) ctx.lookup("java:global/kraken.redshift/UseraccountDao");			
			this.sheduleEjb= (SheduleEjbRemote) ctx.lookup("java:global/kraken.schedule/SheduleEjb");
			
			
			this.stop();
			
			Timestamp dateini =(new Timestamp((new Date()).getTime()));
			
			this.stop();
			
			this.campaignUseraccountDao.postCampaignUseraccounts("DELETE FROM dbo.CAMPAIGN_USERACCOUNT WHERE CAMPAIGN_ID = "+this.campaignUseraccountType.getCampaign().getCampaignId());
			
			this.stop();
			
			this.campaignUseraccountType.setQry(this.campaignUseraccountDao.getGenerateCampaignUseraccountsStringBuilder(this.campaignUseraccountType.getCampaign().getCampaignId()));
			this.campaignUseraccountType.setDateini(dateini);

			this.stop();

			List<Integer> useraccountIds = this.useraccountDao.getUseraccounts(this.campaignUseraccountType.getQry());

			this.stop();

			this.campaignUseraccountType.setSize( useraccountIds.size() );

			this.stop();

			this.scheduleDao.mergeCampaignDateIni(this.campaignUseraccountType.getCampaign().getCampaignId(), this.campaignUseraccountType.getDateini() 
					, (this.campaignUseraccountType.getCampaign().getCampaignExcludeUseraccountCount()!=0)?null:this.campaignUseraccountType.getSize().longValue()
					, this.campaignUseraccountType.getQry().toString() );

			/****REMOVE DADOS ANTIGOS*********************************/
			
			this.stop();
			
			if(!useraccountIds.isEmpty()){
				
				this.stop();
				/****INSERT DE NOVOS DADOS********************************/
				
				for (int i = 0; i < this.campaignUseraccountType.getSize(); i+=CampaignUseraccount.MAX_INSERT_SIZE) {
					
					this.stop();
				
					StringBuilder qryInser = new StringBuilder("INSERT INTO dbo.CAMPAIGN_USERACCOUNT(CAMPAIGN_ID, USERACCOUNTID) \n");
					
					for (Integer useraccountId : useraccountIds.subList(i, (i+CampaignUseraccount.MAX_INSERT_SIZE)>this.campaignUseraccountType.getSize()?this.campaignUseraccountType.getSize():(i+CampaignUseraccount.MAX_INSERT_SIZE)  )) {				
						
						this.stop();
						
						qryInser.append("SELECT "+this.campaignUseraccountType.getCampaign().getCampaignId()+","+useraccountId+" UNION \n");
					}
					
					this.stop();
					
					Throwable error = this.campaignUseraccountDao.postCampaignUseraccounts(qryInser.toString().substring(0, qryInser.toString().lastIndexOf("UNION")));
						
					if(error!=null){
						 throw new Exception(funcoes.getStackTrace(error));
					}
					
				}	
				
			}
			
			this.stop();
			
			if(this.campaignUseraccountType.getCampaign().getCampaignExcludeUseraccountCount()==0){
				this.scheduleDao.mergeCampaignDateFim(this.campaignUseraccountType.getCampaign().getCampaignId(),dateini, (new Timestamp((new Date()).getTime())),this.campaignUseraccountType.getSize().longValue(), this.campaignUseraccountType.getQry().toString());
				SendMail.send(this.campaignUseraccountType.getMail(), "Termino com sucesso da campanha "+this.campaignUseraccountType.getCampaign().getName(), "A campanha "+this.campaignUseraccountType.getCampaign().getName()+", terminou com sucesso! E registrou "+this.campaignUseraccountType.getSize()+" e-mails.", this.campaignUseraccountType.getCc());
			}
			
			this.sheduleEjb.removeJobIds(this.campaignUseraccountType);
			
			useraccountIds=null;
			
			
		} catch (Exception e) {
		
			if(!this.interrupt){
				this.scheduleDao.mergeCampaignErroLog(this.campaignUseraccountType.getCampaign().getCampaignId(),funcoes.getStackTrace(e));
				SendMail.send(this.campaignUseraccountType.getMail(), "Termino com ERROS da campanha "+this.campaignUseraccountType.getCampaign().getName(), funcoes.getStackTrace(e), this.campaignUseraccountType.getCc());
				this.sheduleEjb.removeJobIds(this.campaignUseraccountType);
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
