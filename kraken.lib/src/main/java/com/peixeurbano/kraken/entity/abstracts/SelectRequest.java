package com.peixeurbano.kraken.entity.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.OperationText;
import com.peixeurbano.kraken.entity.imutable.ListOperatorText;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.utils.funcoes;

public abstract class SelectRequest<Source extends BtnDlgSelecaoQry,Target extends BtnDlgSelecaoQry> implements Serializable{

	private static final long serialVersionUID = 5671163161374291324L;
	
	private Campaign campaign;
	private List<Source> sources = new ArrayList<Source>();	
	private List<Target> targets = new ArrayList<Target>();
	private Target target;
	
	/*******SELECAO DE OFERTAS********************************************************************/
	private OperationText operationText = OperationText.CONTEM;
	private List<ListOperatorText> listOperatorTexts = ListOperatorText.getOperationList();
	private String text;
	/*********************************************************************************************/
	private Boolean check = Boolean.FALSE;
	private String sqlPart="";

	public SelectRequest(final Campaign campaign, final List<Target> campaignList) {
		super();
		
		this.campaign = campaign;
		
		this.setTargets(campaignList);
		
		this.operationText = OperationText.CONTEM;
		this.listOperatorTexts = ListOperatorText.getOperationList();
		this.check = Boolean.FALSE;
		this.sqlPart="";


	}
	public List<Source> getSources() {
		return this.sources;
	}

	public void setSources(final List<Source> sources) {	
		
		for (Target target : this.getTargets() ) {
			
			for (Source source: sources) {
				if(source.getId()==target.getId()){
					sources.remove(source);
				}
			}
			
		}
		
		this.sources = sources;		
	}
	
	public Campaign getCampaign() {
		
		return this.campaign;
	}
	
	public void setCampaign(final Campaign campaign) {
		
		this.campaign = campaign;
	}
	
	public void setTarget(final Target target) {
		this.target = target;
	}
	
	public List<Target> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<Target> targets) {
		this.targets = targets;
		if(!this.targets.isEmpty()){
			this.target = this.targets.get(0);
		}
	}
	
	/*******************************************************************/
	
	public OperationText getOperationText() {
		return this.operationText;
	}

	public void setOperationText(final OperationText operationText) {
		this.operationText = operationText;
	}

	public List<ListOperatorText> getListOperatorTexts() {
		return this.listOperatorTexts;
	}

	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public Target getTarget() {
		return this.target;
	}


	public String getSqlPart() {
		
		if(!this.getTargets().isEmpty()){
		StringBuilder part = new StringBuilder("(");		
		for (Target target : this.getTargets()) {
			part.append("'"+target.getId()+"',");
		}
		this.sqlPart=part.substring(0, part.length()-1)+")";
		}
		
		return this.sqlPart;
	}

	/***************************************************/
	public Boolean getCheck() {
		this.check = !this.getTargets().isEmpty();
		return this.check;
	}

	public void setCheck(final Boolean check) {
		this.check = check;
		if(!this.check){
			this.getTargets().clear();
		}
	}
	
	public void setSourcenames(final List<Source> sources){
		for (Source source : sources) {
			for (Target target : this.getTargets()) {
				//if(target.getId().compareTo(source.getId())==0){
				if(funcoes.asciiToLong(target.getId()).intValue()==funcoes.asciiToLong(source.getId()).intValue()){
					target.setValue(source.getValue());
					break;
				}
			}
		}
	}

}
