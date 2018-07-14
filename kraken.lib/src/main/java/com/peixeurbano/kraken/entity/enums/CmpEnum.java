package com.peixeurbano.kraken.entity.enums;

public enum CmpEnum {

	HOURLOCK(0),
	MINUTELOCK(1),
	HOURUNLOCK(2),
	MINUTEUNLOCK(3),
	HOURRELOAD(4),
	MINUTERELOAD(5);
	
    private int valor;

    private CmpEnum(final int valor) {
        
        this.valor = valor;
    }


    public int getValor() {
        return this.valor;
    }
}
