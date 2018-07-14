package com.peixeurbano.kraken.entity.imutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.BooleanEnum;


public class ListBooleanEnum implements Serializable {
	
	private static final long serialVersionUID = 1576739500867436434L;
	
	private BooleanEnum value;
	private String label;
	
	public ListBooleanEnum(final BooleanEnum value, final String label) {
		super();
		this.value = value;
		this.label = label;
	}
	public BooleanEnum getValue() {
		return this.value;
	}
	public void setValue(final BooleanEnum value) {
		this.value = value;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(final String label) {
		this.label = label;
	}   
	
	public static List<ListBooleanEnum>  getOperationList(){
    	
    	List<ListBooleanEnum> lst = new ArrayList<ListBooleanEnum>();
    	
    	lst.add(new ListBooleanEnum(BooleanEnum.VERDADEIRO, BooleanEnum.VERDADEIRO.getLabel()));
    	lst.add(new ListBooleanEnum(BooleanEnum.FALSO, BooleanEnum.FALSO.getLabel()));
    	
    return lst;	
    }
	
	public static List<ListBooleanEnum>  getOperationListCliente(){
    	
    	List<ListBooleanEnum> lst = new ArrayList<ListBooleanEnum>();
    	
    	lst.add(new ListBooleanEnum(BooleanEnum.VERDADEIRO, "Já comprou"));
    	lst.add(new ListBooleanEnum(BooleanEnum.FALSO, "Nunca comprou"));
    	
    return lst;	
    }	
		
}
