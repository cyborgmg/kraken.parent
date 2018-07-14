package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.OffersSelectNav;

@FacesConverter(forClass = OffersSelectNav.class)
public class OffersSelectNavConverter extends SelectNavConverter {}
