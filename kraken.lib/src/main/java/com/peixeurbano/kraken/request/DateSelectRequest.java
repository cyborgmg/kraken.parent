package com.peixeurbano.kraken.request;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.peixeurbano.kraken.entity.enums.OperationDate;
import com.peixeurbano.kraken.entity.enums.SelectIdDate;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.DateSelect;
import com.peixeurbano.kraken.entity.sqlserver.DateSelectDate;
import com.peixeurbano.kraken.entity.sqlserver.DateSelectPeriod;

public class DateSelectRequest implements Serializable {

	private static final long serialVersionUID = -6379589852983861141L;
	
	private DateSelectDate dateSelectDate;
	private DateSelectPeriod dateSelectPeriod;	
	private Boolean pertence = Boolean.TRUE;	
	private String description="";	
	private Boolean check = Boolean.FALSE;
	protected OperationDate operation = OperationDate.NESTA_FAIXA_PERIODO;
	
	public DateSelectRequest(final DateSelect dateSelect) {
	
		this.dateSelectDate = new DateSelectDate(dateSelect);
		this.dateSelectPeriod = new DateSelectPeriod(dateSelect);
		
		if(dateSelect.getClass()==DateSelectDate.class){
			this.toDateOperation(null);
			this.setOperation(this.dateSelectDate.getOperation());
		}else{
			this.toPeriodOperation(null);
			this.setOperation(this.dateSelectDate.getOperation());
		}
		
		if((dateSelect.getOperation()==OperationDate.NESTA_FAIXA_PERIODO)||(dateSelect.getOperation()==OperationDate.NESTA_FAIXA_DATAS)){ 
			this.setPertence(Boolean.TRUE);     
		}else{
			this.setPertence(Boolean.FALSE);       	 	
		}
		
	}
	
	public static DateSelect factory(final SelectIdDate selectId, final Campaign campaign) {
		
		DateSelect dateSelect = new DateSelect();
		dateSelect.setSelectId(selectId);
		dateSelect.setCampaign(campaign);
		dateSelect.setOperation(OperationDate.NESTA_FAIXA_PERIODO);
		dateSelect.getCampaign().addDateSelect(dateSelect);
		
	return dateSelect;
	}
	
	/**AJAXBEHAVIOREVENT********************************************************/
	
	
	public void toDateOperation(final AjaxBehaviorEvent event){		
		this.getDateSelectDate().setDisable( Boolean.FALSE );
		this.getDateSelectPeriod().setDisable( Boolean.TRUE );
		this.getDateSelectPeriod().setCheck( Boolean.FALSE );
	}
	
	public void toPeriodOperation(final AjaxBehaviorEvent event){		
		this.getDateSelectPeriod().setDisable( Boolean.FALSE );
		this.getDateSelectDate().setDisable( Boolean.TRUE );
		this.getDateSelectDate().setCheck( Boolean.FALSE );
		
	}
	
	public void converter(final ActionEvent ae){
		
	    this.getDateSelectPeriod().setEndValue  ( new Double(Days.daysBetween((new DateTime(this.getDateSelectDate().getBeginDate().getTime())),(new DateTime())).getDays()) );
	    this.getDateSelectPeriod().setBeginValue( new Double(Days.daysBetween((new DateTime(this.getDateSelectDate().getEndDate().getTime())),(new DateTime())).getDays()) );
	    
	    if(this.getPertence()){
	    	this.getDateSelectPeriod().setOperation(OperationDate.NESTA_FAIXA_PERIODO);
	    }else{
	    	this.getDateSelectPeriod().setOperation(OperationDate.NAO_NESTA_FAIXA_PERIODO);
	    }
	    
		this.toPeriodOperation(null);
		
	}
	
/****GETS-SETS**********************************************************/

	public DateSelectDate getDateSelectDate() {
		return this.dateSelectDate;
	}
	public void setDateSelectDate(final DateSelectDate dateSelectDate) {
		this.dateSelectDate = dateSelectDate;
	}
	public DateSelectPeriod getDateSelectPeriod() {
		return this.dateSelectPeriod;
	}
	public void setDateSelectPeriod(final DateSelectPeriod dateSelectPeriod) {
		this.dateSelectPeriod = dateSelectPeriod;				
	}
	
	public OperationDate getOperation() {
		return this.operation;
	}

	public void setOperation(final OperationDate operation) {
		this.operation = operation;
	}

	public Boolean getPertence() {
		return this.pertence;
	}
	public void setPertence(final Boolean pertence) {
		this.pertence = pertence;
		
		if(this.pertence){
			
			this.dateSelectDate.setOperation(OperationDate.NESTA_FAIXA_DATAS);
			this.dateSelectPeriod.setOperation(OperationDate.NESTA_FAIXA_PERIODO);
			
		}else{

			this.dateSelectDate.setOperation(OperationDate.NAO_NESTA_FAIXA_DATAS);
			this.dateSelectPeriod.setOperation(OperationDate.NAO_NESTA_FAIXA_PERIODO);			
						
		}
		
		if((this.getOperation()==OperationDate.NESTA_FAIXA_PERIODO)||(this.getOperation()==OperationDate.NAO_NESTA_FAIXA_PERIODO)){
			this.setOperation(this.dateSelectPeriod.getOperation());
		}else{
			this.setOperation(this.dateSelectDate.getOperation());
		}
		
		
	}
	public String getDescription() {
		
		if(this.getDateSelectDate().getCheck()){
			switch (this.dateSelectDate.getOperation()) {		
				case NESTA_FAIXA_DATAS:  
						this.description = this.dateSelectDate.getOperation().getDescription(this.dateSelectDate.getBeginDate().getTime(), this.dateSelectDate.getEndDate().getTime());
						break;	
				case NAO_NESTA_FAIXA_DATAS:  
						this.description = this.dateSelectDate.getOperation().getDescription(this.dateSelectDate.getBeginDate().getTime(), this.dateSelectDate.getEndDate().getTime());
			  		  	break;  
			}
		}
		
		if(this.getDateSelectPeriod().getCheck()){
			switch (this.dateSelectPeriod.getOperation()) {
				case NESTA_FAIXA_PERIODO:  
					this.description = this.dateSelectPeriod.getOperation().getDescription(this.dateSelectPeriod.getBeginValue().longValue(), this.dateSelectPeriod.getEndValue().longValue());
					break;
				case NAO_NESTA_FAIXA_PERIODO: 
					this.description = this.dateSelectPeriod.getOperation().getDescription(this.dateSelectPeriod.getBeginValue().longValue(), this.dateSelectPeriod.getEndValue().longValue());
					break;		
			}
		}
		
		return this.description;
	}

	public Boolean getCheck() {
		this.check = this.getDateSelectPeriod().getCheck()|this.getDateSelectDate().getCheck();
		return this.check;
	}
	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.dateSelectDate.setCheck(Boolean.FALSE);
			this.dateSelectPeriod.setCheck(Boolean.FALSE);
			this.description="";
		}
	}
	
	/***GET-INSTACE*******************************************************************************************************/
	
	public DateSelect getInstace(){
		if(this.getCheck()){
			if(this.getDateSelectDate().getCheck()){
				return this.getDateSelectDate(); 
			}else{
				return this.getDateSelectPeriod();
			}
		}
	return null;	
	}
	
	public String getSqlPart(){
		if(this.getCheck()){
			if(this.getDateSelectDate().getCheck()){
				return this.getDateSelectDate().getSqlPart(); 
			}else{
				return this.getDateSelectPeriod().getSqlPart();
			}
		}
	return "";	
	}
	
}
