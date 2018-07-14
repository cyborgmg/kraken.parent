package com.peixeurbano.kraken.entity.enums;

public enum RightEnum {

	PROCESSAR(0),
	MAXSIZE(1),
	MAILINGCC(2);
	
    private int valor;

    private RightEnum(final int valor) {
        
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }
}
