package com.peixeurbano.kraken.entity.enums;

public enum Status {

	NAO_GERADA(0),
	GERANDO(1),	
	GERADA(2),	
	ERRO(3);

    private Integer valor;
    
    private String label;

    private Status(final Integer valor) {        
        this.valor = valor;
    }

    public Integer getValor() {
        return this.valor;
    }

	public String getLabel() {
		
    	switch (this.getValor()) {
    	
        	case 0: this.label="Não Processada";
        	break;
        	case 1: this.label="Processando...";
			break;
        	case 2: this.label="Processada";
			break;		        	
        	default:this.label="Provável erro, durante o Processamento";
                
    	}
		
		return this.label;
	}
	
}
