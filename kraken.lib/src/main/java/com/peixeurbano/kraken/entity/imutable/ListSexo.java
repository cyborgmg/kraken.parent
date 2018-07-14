package com.peixeurbano.kraken.entity.imutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.Sexo;


public class ListSexo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8139473568830952677L;
	private Sexo value;
	private String label;
	
	public ListSexo(final Sexo value, final String label) {
		super();
		this.value = value;
		this.label = label;
	}
	public Sexo getValue() {
		return this.value;
	}
	public void setValue(final Sexo value) {
		this.value = value;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(final String label) {
		this.label = label;
	}   
	
	public static List<ListSexo>  getOperationList(){
    	
    	List<ListSexo> lst = new ArrayList<ListSexo>();
    	
    	lst.add(new ListSexo(Sexo.M, Sexo.M.getLabel()));
    	lst.add(new ListSexo(Sexo.F, Sexo.F.getLabel()));
    	
    return lst;	
    }
		
}
