package com.peixeurbano.kraken.converter;

import javax.faces.convert.FacesConverter;

import com.peixeurbano.kraken.entity.abstracts.SelectNavConverter;
import com.peixeurbano.kraken.entity.sqlserver.CampaignExclude;

@FacesConverter(forClass = CampaignExclude.class)
public class CampaignExcludeConverter extends SelectNavConverter {}
