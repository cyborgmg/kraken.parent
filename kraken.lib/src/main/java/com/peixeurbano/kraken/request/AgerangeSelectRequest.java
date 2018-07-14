package com.peixeurbano.kraken.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.sqlserver.Agerange;
import com.peixeurbano.kraken.entity.sqlserver.AgerangeSelect;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;

public class AgerangeSelectRequest implements Serializable {

	private static final long serialVersionUID = 5948822770349995928L;
	
	private List<Agerange> ageranges = new ArrayList<Agerange>();
	private Agerange agerange;
	private List<AgerangeSelect> agerangeSelects = new ArrayList<AgerangeSelect>();
	private AgerangeSelect agerangeSelect;
	private Campaign campaign;
	private Boolean check = Boolean.FALSE;
	private String sqlPart="";
	
	
	public AgerangeSelectRequest(final Campaign campaign) {
		super();
		this.campaign = campaign;
		if(this.campaign.getAgerangeSelects()==null){
			this.campaign.setAgerangeSelects(this.getAgerangeSelects());
		}else{
			this.setAgerangeSelects(this.campaign.getAgerangeSelects());
		}
	}
	public AgerangeSelect getAgerangeSelect() {
		return this.agerangeSelect;
	}
	public void setAgerangeSelect(final AgerangeSelect agerangeSelect) {
		this.agerangeSelect = agerangeSelect;
	}
	public List<AgerangeSelect> getAgerangeSelects() {
		return this.agerangeSelects;
	}
	public void setAgerangeSelects(final List<AgerangeSelect> agerangeSelects) {
		this.agerangeSelects = agerangeSelects;
		if(!this.agerangeSelects.isEmpty()){
			this.agerangeSelect=this.agerangeSelects.get(0);
		}	
	}
	public Agerange getAgerange() {
		return this.agerange;
	}
	public void setAgerange(final Agerange agerange) {
		this.agerange = agerange;
	}
	public List<Agerange> getAgeranges() {
		return this.ageranges;
	}
	public void setAgeranges(final List<Agerange> ageranges) {
		this.ageranges = ageranges;
		this.agerange=this.ageranges.get(0);
	}
	
	public Campaign getCampaign() {
		return this.campaign;
	}
	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}
	
	public String getSqlPart() {
		
		if(!this.agerangeSelects.isEmpty()){
		StringBuilder part = new StringBuilder("(");		
		for (AgerangeSelect agerangeSelect : this.agerangeSelects) {
			part.append(agerangeSelect.getAgerange().getAgeId()+",");
		}
		this.sqlPart=part.substring(0, part.length()-1)+")";
		}
		
		return this.sqlPart;
	}
	
	public Boolean getCheck() {
		this.check = !this.getAgerangeSelects().isEmpty();
		return this.check;
	}

	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.getAgerangeSelects().clear();
		}
	}
	
}
