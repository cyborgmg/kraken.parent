package com.peixeurbano.kraken.entity.sqlserver;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



/**
 * The persistent class for the DATE_SELECT_DATE database table.
 * 
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="DATE_ID")
@Table(name="DATE_SELECT_DATE")
@NamedQuery(name="DateSelectDate.findAll", query="SELECT d FROM DateSelectDate d")
public class DateSelectDate extends DateSelect {
	
	@Temporal(TemporalType.DATE)
	@Column(name="BEGIN_DATE")
	private Date beginDate=new Date();

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate=new Date();

	@Transient
	private Boolean disable = Boolean.TRUE;
	
	@Transient
	private Boolean check = Boolean.FALSE;
	
	@Transient
	private String sqlPart="";

	public DateSelectDate() {
	}

	public DateSelectDate(final DateSelect dateSelect) {
		this.setCampaign(dateSelect.getCampaign());
		this.setDateId(dateSelect.getDateId());
		this.setOperation(dateSelect.getOperation());
		this.setSelectId(dateSelect.getSelectId());
		
		if(dateSelect.getClass()==this.getClass()){
			this.disable = ((DateSelectDate)dateSelect).getDisable();
			this.beginDate = ((DateSelectDate)dateSelect).getBeginDate();
			this.endDate = ((DateSelectDate)dateSelect).getEndDate();
		}else{		
			this.disable = Boolean.TRUE;
			this.beginDate=new Date();
			this.endDate=new Date();
		}
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(final Date beginDate) {
		this.beginDate = beginDate!=null?beginDate:(new Date());
		this.endDate=(this.endDate.getTime()<this.beginDate.getTime())?this.beginDate:this.endDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate!=null?endDate:(new Date());
		this.beginDate=(this.endDate.getTime()<this.beginDate.getTime())?this.endDate:this.beginDate;
	}

	public Boolean getDisable() {
		return this.disable;
	}

	public void setDisable(final Boolean disable) {
		this.disable = disable;
	}

	public String getSqlPart() {
		
		this.sqlPart = this.getOperation().getOperation(this.getBeginDate().getTime(), this.getEndDate().getTime()) ;
		return this.sqlPart;
		
	}
	/****************************************/
	
	
	public Boolean getCheck() {	
		this.check = this.getBeginDate().getTime()<this.getEndDate().getTime();
		return this.check;
	}

	
	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.setBeginDate(new Date());
			this.setEndDate(new Date());
		}
	}

}