package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelect;

@FacesConverter(forClass = CategorySelect.class)
public class CategorySelectConverter extends SelectNavConverter {}
