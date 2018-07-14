package com.peixeurbano.kraken.entity.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.serializabes.AbstractParameters;


public abstract class AbstractDao <Source extends BtnDlgSelecaoQry,Target extends BtnDlgSelecaoQry> {
	
	public abstract AbstractParameters getAbstractParameters();
	
	public List<Source> getQuery(final String condition){
		return this.getQuery(condition, null);
	}
	
	public List<Source> getQuery(final String condition, final State state){
		try {
			
			StringBuilder qry = new StringBuilder(this.getAbstractParameters().getQry() +condition);
			
			if(this.getAbstractParameters().getQryState()!=null){
				qry.append(this.getAbstractParameters().getQryState()+"'"+state.getId()+"'");
			}
			
			return this.getAbstractParameters().entityManager.createQuery(qry.toString()).setMaxResults(50).getResultList();
		} catch (final PersistenceException e) {
			e.printStackTrace();
            return new ArrayList<Source>(0);
		}			
	}
	
	public List<Source> getSourceNames(final List<Target> targets) {
		
		StringBuilder part = new StringBuilder();
		
		for (Target target : targets) {
			part.append("'"+target.getId()+"',");
		}
		
		List<Source> sources = this.getAbstractParameters().entityManager.createQuery(this.getAbstractParameters().getQryIn()+part.substring(0, part.length()-1)+")").getResultList();

	return sources;
	}

}
