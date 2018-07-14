package com.peixeurbano.kraken.entity.imutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.OperationValue;


public class ListOperatorValue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2195218375996448174L;
	private OperationValue value;
	private String label;
	
	public ListOperatorValue(final OperationValue value, final String label) {
		super();
		this.value = value;
		this.label = label;
	}
	public OperationValue getValue() {
		return this.value;
	}
	public void setValue(final OperationValue value) {
		this.value = value;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(final String label) {
		this.label = label;
	}   
	
	public static List<ListOperatorValue>  getOperationList(){
    	
    	List<ListOperatorValue> lst = new ArrayList<ListOperatorValue>();
    	
    	lst.add(new ListOperatorValue(OperationValue.IGUAL, "Igual"));
    	lst.add(new ListOperatorValue(OperationValue.DIFERENTE, "Diferente"));
    	lst.add(new ListOperatorValue(OperationValue.MENOR, "Menor"));
    	lst.add(new ListOperatorValue(OperationValue.MENOR_OU_IGUAL, "Menor ou Igual"));
    	lst.add(new ListOperatorValue(OperationValue.MAIOR_OU_IGUAL, "Maior ou Igual"));
    	lst.add(new ListOperatorValue(OperationValue.MAIOR, "Maior"));
    	lst.add(new ListOperatorValue(OperationValue.NAO_ESTA_ENTRE, "Não está entre"));
    	lst.add(new ListOperatorValue(OperationValue.ESTA_ENTRE, "Está entre"));
    	
    return lst;	
    }
		
}
