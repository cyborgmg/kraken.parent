package com.peixeurbano.kraken.entity.enums;

public enum Browser {

	MSIE("Internet Explorer", "MSIE"),
	FIREFOX("Firefox", "Firefox"),
	CHROME("Chrome", "Chrome"),
	OPERA("Opera", "Opera"),
	SAFARI("Safari", "Safari"),
	UNKNOWN("Unknown", "Unknown");


    private String label;

    private String valor;

    private Browser(final String label, final String valor) {
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
