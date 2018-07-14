package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.State;

@Remote
public interface StateDaoRemote {

	List<State> getStates();

}
