package com.peixeurbano.kraken.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.imutable.ListCmpComponent;
import com.peixeurbano.kraken.interfaces.CampaignUseraccountDaoRemote;
import com.peixeurbano.kraken.interfaces.CmpComponentDaoRemote;
import com.peixeurbano.kraken.interfaces.UserDaoRemote;
import com.peixeurbano.kraken.jobs.kraken.CampaignExcludeJob;
import com.peixeurbano.kraken.jobs.kraken.CampaignJob;
import com.peixeurbano.kraken.jobs.kraken.ProcesseCampaingJob;
import com.peixeurbano.kraken.jobs.shedule.InitJob;
import com.peixeurbano.kraken.jobs.shedule.ProcesseSheduleJob;
import com.peixeurbano.kraken.serializabes.CampaignUseraccountType;
import com.peixeurbano.kraken.utils.funcoes;

@Startup
@Singleton
@Named("SheduleBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class SheduleBean implements Serializable {
	
	private static final long serialVersionUID = 7126690166813225721L;

	@EJB(lookup = "java:global/kraken.sqlserver/CmpComponentDao")
	private CmpComponentDaoRemote cmpComponentDao;
	
	@EJB(lookup = "java:global/kraken.sqlserver/CampaignUseraccountDao")
	private CampaignUseraccountDaoRemote campaignUseraccountDao;
	
	@EJB(lookup = "java:global/kraken.sqlserver/UserDao")
	private UserDaoRemote userDao;
	
	private ListCmpComponent listCmpComponent = new ListCmpComponent();
	
	private final List<CampaignUseraccountType> jobIds = new ArrayList<CampaignUseraccountType>();
	private final List<CampaignUseraccountType> excludeJobIds = new ArrayList<CampaignUseraccountType>();
	
	private Scheduler scheduler;
	
	public SheduleBean(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	@PostConstruct
	public void init() {
		
		try {
			
			this.listCmpComponent = this.cmpComponentDao.populateParams();
			
			if(this.scheduler!=null){
				this.scheduler.clear();
			}
			
			this.scheduler = new StdSchedulerFactory().getScheduler();
			this.scheduler.start();
		
			this.scheduler.scheduleJob(JobBuilder.newJob( InitJob.class ).withIdentity("TAgendadorInitJob", "GRAgendadorInitJob").build(), 
				TriggerBuilder.newTrigger().withIdentity("GTAgendadorInitJob", "GRAgendadorInitJob")
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(Integer.parseInt(this.listCmpComponent.getHOURRELOAD().getValue()), Integer.parseInt(this.listCmpComponent.getMINUTERELOAD().getValue()))).withPriority(9).build());
		
			this.scheduler.scheduleJob(JobBuilder.newJob( ProcesseSheduleJob.class ).withIdentity("TAgendadorProcesseJob", "GRAgendadorProcesseJob").build(), 
				TriggerBuilder.newTrigger().withIdentity("GTAgendadorProcesseJob", "GRAgendadorProcesseJob")
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(Integer.parseInt(this.listCmpComponent.getHOURLOCK().getValue()), Integer.parseInt(this.listCmpComponent.getMINUTELOCK().getValue()))).withPriority(9).build());
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	public void processerJob() {
	try {
					
		String tarefaName = "TProcesserJob"; 
		String group = "GRProcesserJob";
		String gatilho = "GTProcesserJob";
		
		JobDetail job = JobBuilder.newJob( ProcesseCampaingJob.class ).withIdentity(tarefaName, group).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(gatilho, group).withPriority(9).build();
		
		
		JobDetail interruptedJob = JobBuilder.newJob( ProcesseCampaingJob.class ).withIdentity(tarefaName, group).build();
		this.scheduler.interrupt(interruptedJob.getKey());
		this.scheduler.deleteJob(interruptedJob.getKey());
		interruptedJob=null;
	
		this.scheduler.scheduleJob(job,trigger);
			
	} catch (SchedulerException e) {
		e.printStackTrace();
	}
	}
	
	/*********************************************************************/
	public boolean scheduleCampain(final CampaignUseraccountType campaignUseraccountType){
		
		this.campaignUseraccountDao.mergeCampaign(campaignUseraccountType.getCampaign().getCampaignId(), Status.GERANDO, null);
		
		String tarefaName = "TCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId(); 
		String group = "GRCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId();
		String gatilho = "GCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId();
		
		/************************************************************************************************************************/
		
		JobDetail job = JobBuilder.newJob( CampaignJob.class ).withIdentity(tarefaName, group).build();
		campaignUseraccountType.setCc(this.userDao.getMailscc());
		job.getJobDataMap().put("campaignUseraccountType", campaignUseraccountType );
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(gatilho, group).withPriority(9).build();
		
		try {			
			
			JobDetail interruptedJob = JobBuilder.newJob( CampaignJob.class ).withIdentity(tarefaName, group).build();
			this.scheduler.interrupt(interruptedJob.getKey());
			this.scheduler.deleteJob(interruptedJob.getKey());
			interruptedJob=null;
			
			this.addJobIds(campaignUseraccountType);
			this.scheduler.scheduleJob(job, trigger);
			
			return true;
		} catch (SchedulerException e) {
	
			this.campaignUseraccountDao.mergeCampaign(campaignUseraccountType.getCampaign().getCampaignId(), Status.ERRO, funcoes.getStackTrace(e));
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*********************************************************************/
	
	private void scheduleExcludeCampain() {
		for (CampaignUseraccountType campaignUseraccountType : this.excludeJobIds) {
		
	    	this.campaignUseraccountDao.mergeCampaign(campaignUseraccountType.getCampaign().getCampaignId(), Status.GERANDO, null);
	    	Class<CampaignExcludeJob> jobclass = CampaignExcludeJob.class;
			String tarefaName = "TCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId(); 
			String group = "GRCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId();
			String gatilho = "GCAMPAIGN"+campaignUseraccountType.getCampaign().getCampaignId();
	
			JobDetail job = JobBuilder.newJob( jobclass ).withIdentity(tarefaName, group).build();
			
			job.getJobDataMap().put("campaignUseraccountType", campaignUseraccountType);
			
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(gatilho, group).withPriority(9).build();
	
			try {			
				this.scheduler.deleteJob(job.getKey());
				this.scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				this.campaignUseraccountDao.mergeCampaign(campaignUseraccountType.getCampaign().getCampaignId(), Status.ERRO, funcoes.getStackTrace(e));
				e.printStackTrace();
				
			}
		
		}
		
	}
	
	/*********************************************************************/
	
	public void addJobIds(final CampaignUseraccountType campaignUseraccountType) {
		
		if(this.findCampaingId(campaignUseraccountType.getCampaign().getCampaignId())==null){
			this.jobIds.add( campaignUseraccountType );
		}    	
	}
	
	private void addExcludeJobIds(final CampaignUseraccountType campaignUseraccountType) {
		
		if(this.findExcludeCampaingId(campaignUseraccountType.getCampaign().getCampaignId())==null){
			
			if(campaignUseraccountType.getCampaign().getCampaignExcludeUseraccountCount()>0){
				this.excludeJobIds.add( campaignUseraccountType );
			}	
			if(this.jobIds.size()==0){
				this.scheduleExcludeCampain();
			}
		} 
		
	}
	
	
	public void removeJobIds(final CampaignUseraccountType campaignUseraccountType) {    	
		
		if( this.jobIds.remove( this.findCampaingId(campaignUseraccountType.getCampaign().getCampaignId()) ) ){
			this.addExcludeJobIds(campaignUseraccountType);
		}
		
	}
	
	
	public void removeExcludeJobIds(final CampaignUseraccountType campaignUseraccountType) {
		
		this.excludeJobIds.remove( this.findExcludeCampaingId(campaignUseraccountType.getCampaign().getCampaignId()) ) ;
		
	}
	
	
	public CampaignUseraccountType findExcludeCampaingId(final Integer campaingId) {
		
		for (CampaignUseraccountType campaignUseraccountType : this.excludeJobIds) {
			if(campaignUseraccountType.getCampaign().getCampaignId().intValue()==campaingId.intValue()){
				return campaignUseraccountType;
			}
		}
		return null;
	}
	
	
	public CampaignUseraccountType findCampaingId(final Integer campaingId) {
		
		for (CampaignUseraccountType campaignUseraccountType : this.jobIds) {
			if(campaignUseraccountType.getCampaign().getCampaignId().intValue()==campaingId.intValue()){
				return campaignUseraccountType;
			}
		}
		return null;
	}
	
	
	public CampaignUseraccountType findAllCampaingId(final Integer campaignId) {
		
		List<CampaignUseraccountType> allJobIds = funcoes.cloneList(this.jobIds);
		allJobIds.addAll(this.excludeJobIds);
		
		for (CampaignUseraccountType campaignUseraccountType : allJobIds) {
			if(campaignUseraccountType.getCampaign().getCampaignId().intValue()==campaignId.intValue()){
				return campaignUseraccountType;
			}
		}
		return null;
	}
	
	public Integer getJobsSize() {    	
		return this.jobIds.size()+this.excludeJobIds.size();		
	}
	
	
}
