package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.redshift.kraken.State;

@FacesConverter(forClass = State.class)
public class StateConverter extends SelectNavConverter {}
