package com.peixeurbano.kraken.jobs.shedule;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;

@TransactionManagement(TransactionManagementType.BEAN)

public class ProcesseSheduleJob implements Job {	

	
	public SheduleEjbRemote sheduleEjb;
	

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
				
		try {

			Context ctx = new InitialContext();
			this.sheduleEjb= (SheduleEjbRemote) ctx.lookup("java:global/kraken.schedule/SheduleEjb");
			this.sheduleEjb.processerJob();
			
		} catch (Exception e) {		
				e.printStackTrace();				
		}	
		
	}

	
}
