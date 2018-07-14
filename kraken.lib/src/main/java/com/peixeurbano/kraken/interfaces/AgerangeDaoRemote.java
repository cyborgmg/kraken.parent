package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.sqlserver.Agerange;

@Remote
public interface AgerangeDaoRemote {

	List<Agerange> getAll();

}
