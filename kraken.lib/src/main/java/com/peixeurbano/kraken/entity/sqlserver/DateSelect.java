package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.enums.OperationDate;
import com.peixeurbano.kraken.entity.enums.SelectIdDate;



/**
 * The persistent class for the DATE_SELECT database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="DATE_SELECT")
@NamedQuery(name="DateSelect.findAll", query="SELECT d FROM DateSelect d")
public class DateSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DATE_ID")
	private Integer dateId;

	@Column(name="OPERACAO")
	protected OperationDate operation = OperationDate.NESTA_FAIXA_PERIODO;

	@Column(name="SELECT_ID")
	private SelectIdDate selectId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;
	
//	@Transient
//	private Boolean check = Boolean.FALSE;
	
	public DateSelect() {
	}

	public DateSelect(final DateSelect dateSelect) {

		this.setDateId(dateSelect.getDateId());
		this.setOperation(dateSelect.getOperation());
		this.setSelectId(dateSelect.getSelectId());
		this.setCampaign(dateSelect.getCampaign());
		//this.check = Boolean.FALSE;
	}

	
	public Integer getDateId() {
		return this.dateId;
	}

	public void setDateId(final Integer dateId) {
		this.dateId = dateId;
	}

	public OperationDate getOperation() {		
		return this.operation;
	}

	public void setOperation(final OperationDate operation) {
		this.operation = operation!=null?operation:this.operation;
	}

	public SelectIdDate getSelectId() {
		return this.selectId;
	}

	public void setSelectId(final SelectIdDate selectId) {
		this.selectId = selectId;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}
	/*
	public Boolean getCheck() {
		return this.check;
	}

	public void setCheck(final Boolean check) {
		this.check = check;
	}
	*/
}