package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.enums.OperationValue;
import com.peixeurbano.kraken.entity.enums.SelectIdValue;


/**
 * The persistent class for the VALUE_SELECT database table.
 * 
 */
@Entity
@Table(name="VALUE_SELECT")
@NamedQuery(name="ValueSelect.findAll", query="SELECT v FROM ValueSelect v")
public class ValueSelect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VALUE_ID")
	private Integer valueId;

	@Column(name="BEGIN_VALUE")
	private Double beginValue=0D;

	@Column(name="END_VALUE")
	private Double endValue=0D;

	@Column(name="OPERACAO")
	private OperationValue opetation=OperationValue.IGUAL;
	
	@Column(name="SELECT_ID")
	private SelectIdValue selectId;

	@Transient
	private String opetationDesc="";

	@Transient
	private Boolean endValueDisable = Boolean.TRUE;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;

	@Transient
	private Boolean check = Boolean.TRUE;	
	
	@Transient
	private String sqlPart="";
	
	public ValueSelect() {
	}

	public ValueSelect(final SelectIdValue selectId, final Campaign campaign) {
		super();
		this.selectId = selectId;
		this.campaign=campaign;
		this.campaign.addValueSelect(this);
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public Integer getValueId() {
		return this.valueId;
	}

	public void setValueId(final Integer valueId) {
		this.valueId = valueId;
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

	public OperationValue getOpetation() {
		return this.opetation;
	}

	public void setOpetation(final OperationValue opetation) {
		this.opetation = opetation;
		this.setEndValueDisable( !((this.opetation==OperationValue.ESTA_ENTRE)||(this.opetation==OperationValue.NAO_ESTA_ENTRE)) ); 
	}

	public SelectIdValue getSelectId() {
		return this.selectId;
	}

	public void setSelectId(final SelectIdValue selectId) {
		this.selectId = selectId;
	}

	public String getOpetationDesc() {
		
		if(this.opetation!=null){
			this.opetationDesc = (this.beginValue+this.endValue)<1?"":this.opetation.getDescription(this.beginValue.intValue(), this.endValue.intValue());
		}
		return this.opetationDesc;
	}

	public Boolean getEndValueDisable() {
		return this.endValueDisable;
	}

	private void setEndValueDisable(final Boolean endValueDisable) {
		this.endValueDisable = endValueDisable;
		this.endValue=this.endValueDisable?0:this.endValue;
	}

	public String getSqlPart() {

		if((this.getOpetation()==OperationValue.ESTA_ENTRE)||(this.getOpetation()==OperationValue.NAO_ESTA_ENTRE)){
			this.sqlPart=this.getOpetation().getOperation(this.getBeginValue().intValue(), this.getEndValue().intValue());
		}else{
			this.sqlPart=this.getOpetation().getOperation(this.getBeginValue().intValue());
		}
		return this.sqlPart;
	}

	public Boolean getCheck() {
		this.check = (this.getBeginValue()+this.getEndValue())>0;
		return this.check;
	}

	public void setCheck(final Boolean check) {
		this.check = check;
		
		if(!this.check){
			this.setBeginValue(0D);
			this.setEndValue(0D);			
		}

	}	
	
}