package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.CategorySelectNav;

@FacesConverter(forClass = CategorySelectNav.class)
public class CategorySelectNavConverter extends SelectNavConverter {}
