package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UseraccountDaoRemote {

	List<Integer> getUseraccounts(StringBuilder qry);

}