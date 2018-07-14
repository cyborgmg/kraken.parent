package com.peixeurbano.kraken.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.redshift.kraken.Mailinglist;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.MailinglistSelect;


public class MailinglistSelectRequest implements Serializable {
	
	private static final long serialVersionUID = 3055076504750434360L;
	
	private List<Mailinglist> mailinglists = new ArrayList<Mailinglist>(); 
	private Mailinglist mailinglist;
	private List<MailinglistSelect> mailinglistSelects = new ArrayList<MailinglistSelect>();
	private MailinglistSelect mailinglistSelect;
	private Campaign campaign;
	private Boolean check = Boolean.FALSE;
	private String sqlPart="";
	
	public MailinglistSelectRequest(final Campaign campaign) {
		super();
		this.campaign = campaign;
		if(this.campaign.getMailinglistSelects()==null){
			this.campaign.setMailinglistSelects(this.getMailinglistSelects());
		}else{
			this.setMailinglistSelects(this.campaign.getMailinglistSelects());
		}
	}
	
	
	public MailinglistSelect getMailinglistSelect() {
		return this.mailinglistSelect;
	}
	public void setMailinglistSelect(final MailinglistSelect mailinglistSelect) {
		this.mailinglistSelect = mailinglistSelect;
	}
	public List<MailinglistSelect> getMailinglistSelects() {
		return this.mailinglistSelects;
	}

	public void setMailinglistSelects(final List<MailinglistSelect> mailinglistSelects) {
		this.mailinglistSelects = mailinglistSelects;
		if(!this.mailinglistSelects.isEmpty()){
			this.mailinglistSelect = this.mailinglistSelects.get(0);
		}
	}
	public Mailinglist getMailinglist() {
		return this.mailinglist;
	}
	public void setMailinglist(final Mailinglist mailinglist) {
		this.mailinglist = mailinglist;
	}
	public List<Mailinglist> getMailinglists() {
		return this.mailinglists;
	}
	public void setMailinglists(final List<Mailinglist> mailinglists) {
		this.mailinglists = mailinglists;
		this.setMailinglist(this.mailinglists.get(0));
	}
	
	public Campaign getCampaign() {
		return this.campaign;
	}
	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}
	
	public String getSqlPart() {
		
		if(!this.getMailinglistSelects().isEmpty()){
		StringBuilder part = new StringBuilder("(");		
		for (MailinglistSelect mailinglistselect : this.getMailinglistSelects()) {
			part.append(mailinglistselect.getMailinglistId()+",");
		}
		this.sqlPart=part.substring(0, part.length()-1)+")";
		}
		
		return this.sqlPart;
	}	
	
	public Boolean getCheck() {
		this.check = !this.mailinglistSelects.isEmpty();
		return this.check;
	}
	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.getMailinglistSelects().clear();
		}
	}
	
	public void setMailinglistnames(final List<Mailinglist> mailinglists){
			
		for (MailinglistSelect mailinglistSelect : this.getCampaign().getMailinglistSelects()) {
			for (Mailinglist mailinglist : mailinglists) {
				if(mailinglist.getMailinglistid().intValue()==mailinglistSelect.getMailinglistId().intValue()){
					mailinglistSelect.setMailinglistname(mailinglist.getMailinglistname());
				}
			}
		}

	}
}
