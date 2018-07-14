package com.peixeurbano.kraken.entity.enums;

public enum BooleanEnum {

	FALSO(0),
	VERDADEIRO(1);

    private Integer valor;
    
    private String label;

    private BooleanEnum(final Integer valor) {        
        this.valor = valor;
    }

    public Integer getValor() {
        return this.valor;
    }

	public String getLabel() {
		
    	switch (this.getValor()) {
    	
        	case 0: this.label="Não";
        			break;
        	default:this.label="Sim";
                
    	}
		
		return this.label;
	}
	
	
	public boolean convertTobollean(){
		return this.getValor()==BooleanEnum.VERDADEIRO.valor?true:false;
	}
}
