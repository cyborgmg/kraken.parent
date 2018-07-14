package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.AttributeSelectNav;

@FacesConverter(forClass = AttributeSelectNav.class)
public class AttributeSelectNavConverter extends SelectNavConverter {}
