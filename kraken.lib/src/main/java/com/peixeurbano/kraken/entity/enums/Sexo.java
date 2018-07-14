package com.peixeurbano.kraken.entity.enums;

public enum Sexo {

    F("Feminino", "F"),

    M("Masculino", "M");


    private String label;

    private String valor;

    private Sexo(final String label, final String valor) {
        this.label = label;
        this.valor = valor;
    }

    public String getLabel() {
        return this.label;
    }

    public String getValor() {
        return this.valor;
    }
}
