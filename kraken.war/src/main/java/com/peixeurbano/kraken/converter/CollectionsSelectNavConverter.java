package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.CollectionsSelectNav;

@FacesConverter(forClass = CollectionsSelectNav.class)
public class CollectionsSelectNavConverter extends SelectNavConverter {}
