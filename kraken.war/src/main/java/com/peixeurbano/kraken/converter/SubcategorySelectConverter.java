package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.SubcategorySelect;

@FacesConverter(forClass = SubcategorySelect.class)
public class SubcategorySelectConverter extends SelectNavConverter {}
