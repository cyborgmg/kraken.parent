package com.peixeurbano.kraken.entity.sqlserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the DATE_SELECT_PERIOD database table.
 * 
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="DATE_ID")
@Table(name="DATE_SELECT_PERIOD")
@NamedQuery(name="DateSelectPeriod.findAll", query="SELECT d FROM DateSelectPeriod d")
public class DateSelectPeriod extends DateSelect {
	
	@Column(name="BEGIN_VALUE")
	private Double beginValue=new Double(0);

	@Column(name="END_VALUE")
	private Double endValue=new Double(0);
	
	@Transient
	private Boolean disable = Boolean.FALSE;
	
	@Transient
	private Boolean check = Boolean.FALSE;
	
	@Transient
	private String sqlPart="";
	
	public DateSelectPeriod() {
	}
	
	public DateSelectPeriod(final DateSelect dateSelect) {
		
		this.setCampaign(dateSelect.getCampaign());
		this.setDateId(dateSelect.getDateId());
		this.setOperation(dateSelect.getOperation());
		this.setSelectId(dateSelect.getSelectId());
		
		if(dateSelect.getClass()==this.getClass()){
			this.disable = ((DateSelectPeriod)dateSelect).getDisable();
			this.beginValue = ((DateSelectPeriod)dateSelect).getBeginValue();
			this.endValue = ((DateSelectPeriod)dateSelect).getEndValue();
		}else{		
			this.disable = Boolean.FALSE;
			this.beginValue=new Double(0);
			this.endValue=new Double(0);
		}
		
	}
	
	public Double getBeginValue() {
		return this.beginValue;
	}

	public void setBeginValue(final Double beginValue) {
		this.beginValue = beginValue!=null?beginValue:0;
		this.endValue=(this.endValue<this.beginValue)?this.beginValue:this.endValue;
	}

	public Double getEndValue() {
		return this.endValue;
	}

	public void setEndValue(final Double endValue) {
		this.endValue = endValue!=null?endValue:0;
		this.beginValue=(this.endValue<this.beginValue)?this.endValue:this.beginValue;
	}

	public Boolean getDisable() {
		return this.disable;
	}

	public void setDisable(final Boolean disable) {
		this.disable = disable;
	}

	public String getSqlPart() {
		this.sqlPart = this.getOperation().getOperation(this.getBeginValue().longValue(), this.getEndValue().longValue()) ;
		return this.sqlPart;
	}
	
	/****************************************/
	
	
	public Boolean getCheck() {
		this.check = this.getBeginValue()<this.getEndValue();
		return this.check;
	}

	
	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.setBeginValue(new Double(0));
			this.setEndValue(new Double(0));
		}
	}


}