package com.peixeurbano.kraken.jobs.kraken;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.interfaces.ScheduleDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;

@TransactionManagement(TransactionManagementType.BEAN)
public class ProcesseCampaingJob implements Job {	

	private ScheduleDaoRemote scheduleDao;
	public SheduleEjbRemote sheduleEjb;
	
	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
				
		try {

			Context ctx = new InitialContext();
			this.scheduleDao= (ScheduleDaoRemote) ctx.lookup("java:global/kraken.sqlserver/ScheduleDao");			
			this.sheduleEjb= (SheduleEjbRemote) ctx.lookup("java:global/kraken.schedule/SheduleEjb");
			
			for (Campaign campaign : this.scheduleDao.getSchedules() ){

				if(campaign.getUsableCheck()){
					
					this.sheduleEjb.scheduleCampain(new CampaignUseraccountType(campaign, null, null, null, "kraken.peixeurbano@gmail.com", null));
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
				
	}
	
}
