package com.peixeurbano.kraken.entity.enums;

public enum OperationValue {

	IGUAL(0),
	DIFERENTE(1),
	MENOR(2),
	MENOR_OU_IGUAL(3),
	MAIOR_OU_IGUAL(4),	
	MAIOR(5),
	NAO_ESTA_ENTRE(6),
	ESTA_ENTRE(7);
	

    private Integer valor;

    private OperationValue(final Integer valor) {
        
        this.valor = valor;
    }

    public Integer getValor() {
        return this.valor;
    }
    
    public String getOperation(final Integer value){
    	
    	switch (this.getValor()) {
    	
        case 0:  return "="+String.valueOf(value);
              
        case 1:  return "<>"+String.valueOf(value);
              
        case 2:  return ">"+String.valueOf(value);
              
        case 3:  return ">="+String.valueOf(value);
              
        case 4:  return "<="+String.valueOf(value);
              
        default:  return "<"+String.valueOf(value);
                 
    	}
    	
    }
    
    public String getOperation(final Integer ini, final Integer fim){
    	
    	switch (this.getValor()) {
    	              
        case 6:  return "not between "+String.valueOf(ini)+" and "+String.valueOf(fim);
              
        default:  return "between "+String.valueOf(ini)+" and "+String.valueOf(fim);
                 
    	}
    	
    }
    
    public String getDescription(final Integer ini, final Integer fim){

    	if(ini!=null){
    	
    	switch (this.getValor()) {
    	
        case 0:  return "Igual a "+String.valueOf(ini);
              
        case 1:  return "Diferente de "+String.valueOf(ini);
              
        case 2:  return "Menor que "+String.valueOf(ini);
              
        case 3:  return "Meno ou Igual que "+String.valueOf(ini);
              
        case 4:  return "Maio ou Igual que "+String.valueOf(ini);
        
        case 5:  return "Maior que "+String.valueOf(ini);
        
        case 6:  return "Não Está entre "+String.valueOf(ini)+" e "+String.valueOf(fim);
              
        default:  return "Está entre "+String.valueOf(ini)+" e "+String.valueOf(fim);
                 
    	}    	
    	
    	}else{
    		return "";
    	}
    	
    }
    
    
    
}
