package com.peixeurbano.kraken.entity.abstracts;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.utils.funcoes;

public class SelectMethod <Source extends BtnDlgSelecaoQry, Target extends BtnDlgSelecaoQry> implements Serializable {

	private static final long serialVersionUID = -7436088007497601235L;
	
	private final SelectRequest selectRequest; 
	private final AbstractDao dao;
	private final Campaign campaign;
	private final Target target;

	public SelectMethod(final SelectRequest selectRequest, final AbstractDao dao, final Campaign campaign, final Target target) {
		super();
		this.selectRequest = selectRequest;
		this.dao = dao;
		this.campaign=campaign;
		this.target=target;
	}

	public void getQuery(final ActionEvent ae){
		
		this.selectRequest.setSources(this.dao.getQuery(this.selectRequest.getOperationText().getOperation(this.selectRequest.getText())) );
	}

	public void addSelects(final ActionEvent ae) {
		
		List<Source> sourceClone = funcoes.cloneList(this.selectRequest.getSources());
		
		for (Source source : sourceClone ) {
			if(source.getCheck()){
				
				Target newTarget = (Target) funcoes.clone(this.target);
				
				newTarget.constructor( (int) (Math.round((Math.random()*10000))*(-1)), this.campaign, source.getId(), source.getValue() );
				
				this.selectRequest.getTargets().add(newTarget);
				this.selectRequest.setTarget(newTarget);
			}	
		}
		
		for (Source source : sourceClone ) {
			if(source.getCheck()){
				this.selectRequest.getSources().remove(source);
			}	
		}
		
	}
	
	public void removeSelect(final ActionEvent ae){
		
		if(!this.selectRequest.getTargets().isEmpty()){
			this.selectRequest.getTargets().remove(this.selectRequest.getTarget());
		}
		
		if(!this.selectRequest.getTargets().isEmpty()){
			this.selectRequest.setTarget((BtnDlgSelecaoQry) this.selectRequest.getTargets().get(0));
		}
		
	}
	
	public void restore(){
		
		
		if((this.selectRequest!=null)?this.selectRequest.getCheck():false){
			this.selectRequest.setSourcenames(this.dao.getSourceNames(this.selectRequest.getTargets()));
		}
	}

	public SelectRequest getSelectRequest() {
		return this.selectRequest;
	}

}
