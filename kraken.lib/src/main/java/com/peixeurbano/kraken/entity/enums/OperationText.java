package com.peixeurbano.kraken.entity.enums;

public enum OperationText {

	IGUAL(0),
	DIFERENTE_DE(1),
	COMECA_COM(2), 
	NAO_COMECA_COM(3),
	TERMINA_COM(4),
	NAO_TERMINA_COM(5),
	CONTEM(6),
	NAO_CONTEM(7);	

    private int valor;

    private OperationText(final int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }
    
    public String getOperation(final String text){
    	
    	switch (this.getValor()) {
    	
        case 0:  return "= upper('"+text+"')";
              
        case 1:  return "<> upper('"+text+"')";
              
        case 2:  return "like upper('"+text+"%')";
              
        case 3:  return "not like upper('"+text+"%')";
              
        case 4:  return "like upper('%"+text+"')";
              
        case 5:  return "not like upper('%"+text+"')";
        
        case 6:  return "like upper('%"+text+"%')";
        
        default: return "not like upper('%"+text+"%')";
                 
    	}
    	
    }
    
    public String getLabel(String text){
    	
    	text=(text==null)?"":text;    	
    	if("".equals(text)){
    		return " ";
    	}
    	
    	switch (this.getValor()) {
    	
        case 0:  return "Iglual a "+text;
              
        case 1:  return "Diferente de "+text;
              
        case 2:  return "Começa com "+text;
              
        case 3:  return "Não Começa com "+text;
              
        case 4:  return "Termina com "+text;
              
        case 5:  return "Não Termina com "+text;
        
        case 6:  return "Está entre "+text;
        
        case 7: return "Não Está entre "+text;
        
        default: return " ";
                 
    	}
    	
    }
    
    
}
