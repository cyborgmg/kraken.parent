package com.peixeurbano.kraken.entity.enums;

public enum SelectIdValue {


	RECEITA_LIQUIDA_COMPRAS(0),
	QUANTIDADE_COMPRAS(1),
	MAIOR_VALOR_COMPRA(2),
	VALOR_UTILIZADO_VALE_PRESENTE(3);
	

    private int valor;

    private SelectIdValue(final int valor) {
        
        this.valor = valor;
    }


    public int getValor() {
        return this.valor;
    }
}
