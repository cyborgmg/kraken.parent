package com.peixeurbano.kraken.entity.enums;

public enum SelectIdDate {


	DATA_CADASTRO(0),
	DATA_ANIVERSARIO(1),
	DATA_ULTIMA_COMPRA(2),
	DATA_NAVIGATION(3);

    private int valor;

    private SelectIdDate(final int valor) {
        
        this.valor = valor;
    }


    public int getValor() {
        return this.valor;
    }
}
