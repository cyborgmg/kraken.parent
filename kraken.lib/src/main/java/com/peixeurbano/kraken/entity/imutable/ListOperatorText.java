package com.peixeurbano.kraken.entity.imutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.OperationText;

public class ListOperatorText implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2112121503214838299L;
	private OperationText value;
	private String label;
	
	public ListOperatorText(final OperationText value, final String label) {
		super();
		this.value = value;
		this.label = label;
	}
	public OperationText getValue() {
		return this.value;
	}
	public void setValue(final OperationText value) {
		this.value = value;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(final String label) {
		this.label = label;
	}   
	
	public static List<ListOperatorText>  getOperationList(){
    	
    	List<ListOperatorText> lst = new ArrayList<ListOperatorText>();
    	
    	lst.add(new ListOperatorText(OperationText.IGUAL, "Igual"));
    	lst.add(new ListOperatorText(OperationText.DIFERENTE_DE, "Diferente de"));
    	lst.add(new ListOperatorText(OperationText.COMECA_COM, "Começa com"));
    	lst.add(new ListOperatorText(OperationText.NAO_COMECA_COM, "Não começa com"));
    	lst.add(new ListOperatorText(OperationText.TERMINA_COM, "Termina com"));
    	lst.add(new ListOperatorText(OperationText.NAO_TERMINA_COM, "Não termina com"));
    	lst.add(new ListOperatorText(OperationText.CONTEM, "Contem"));
    	lst.add(new ListOperatorText(OperationText.NAO_CONTEM, "Não contem"));
    	
    return lst;	
    }
		
}
