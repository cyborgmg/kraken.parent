package com.peixeurbano.kraken.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;

import com.peixeurbano.kraken.build;
import com.peixeurbano.kraken.entity.imutable.ListCmpComponent;
import com.peixeurbano.kraken.entity.redshift.kraken.Mailinglist;
import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.entity.sqlserver.Agerange;
import com.peixeurbano.kraken.interfaces.AgerangeDaoRemote;
import com.peixeurbano.kraken.interfaces.CmpComponentDaoRemote;
import com.peixeurbano.kraken.interfaces.MailingListDaoRemote;
import com.peixeurbano.kraken.interfaces.SheduleEjbRemote;
import com.peixeurbano.kraken.interfaces.StateDaoRemote;

@Startup
@Singleton
@Named("aplicationBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class AplicationBean implements Serializable {
	
	private static final long serialVersionUID = -602553226397014329L;

	private static final String vBuild = build.VBUILD;

	@EJB(lookup = "java:global/kraken.redshift/StateDao")
	private StateDaoRemote stateDao;

	@EJB(lookup = "java:global/kraken.sqlserver/AgerangeDao")
	private AgerangeDaoRemote agerangeDao;
	
	@EJB(lookup = "java:global/kraken.redshift/MailingListDao")
	private MailingListDaoRemote mailingListDao;
	
	private List<Mailinglist> mailinglists = new ArrayList<Mailinglist>(); 	
	private Mailinglist mailinglist;
	
	private List<Agerange> ageranges = new ArrayList<Agerange>();
	private Agerange agerange;
	
	private List<State> states = new ArrayList<State>();
	
	@EJB(lookup = "java:global/kraken.sqlserver/CmpComponentDao")
	private CmpComponentDaoRemote cmpComponentDao;
	
	@EJB(lookup = "java:global/kraken.schedule/SheduleEjb")
	private SheduleEjbRemote sheduleEjb;
	
	private ListCmpComponent listCmpComponent = new ListCmpComponent();
	
	private int sheduleEjbJobsSize;
	
	@PostConstruct
	@Schedule(hour = "6", minute = "0", second = "0", persistent = false)
	public void init(){
		
		this.listCmpComponent = this.cmpComponentDao.populateParams();
		
		this.setMailinglists(this.mailingListDao.getAll());
		if(!this.getMailinglists().isEmpty()){
			this.setMailinglist(this.getMailinglists().get(0));
		}
		
		this.setAgeranges(this.agerangeDao.getAll());
		if(!this.getAgeranges().isEmpty()){
			this.setAgerange(this.getAgeranges().get(0));
		}
		
		this.setStates(this.stateDao.getStates());
		this.getStates().add(0,new State("Todos", "%"));
		
	}
	
	public int getSheduleEjbJobsSize() {
		this.sheduleEjbJobsSize = this.sheduleEjb.getJobsSize();
		return this.sheduleEjbJobsSize;
	}

	public List<Mailinglist> getMailinglists() {
		return this.mailinglists;
	}

	private void setMailinglists(final List<Mailinglist> mailinglists) {
		this.mailinglists = mailinglists;
	}

	public Mailinglist getMailinglist() {
		return this.mailinglist;
	}

	private void setMailinglist(final Mailinglist mailinglist) {
		this.mailinglist = mailinglist;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(final List<State> states) {
		this.states = states;
	}

	public List<Agerange> getAgeranges() {
		return this.ageranges;
	}

	private void setAgeranges(final List<Agerange> ageranges) {
		this.ageranges = ageranges;
	}

	public Agerange getAgerange() {
		return this.agerange;
	}

	private void setAgerange(final Agerange agerange) {
		this.agerange = agerange;
	}
    
	public ListCmpComponent getListCmpComponent() {
		return this.listCmpComponent;
	}
    
	public String getvBuild() {
		return vBuild;
	}	
	
	
}
