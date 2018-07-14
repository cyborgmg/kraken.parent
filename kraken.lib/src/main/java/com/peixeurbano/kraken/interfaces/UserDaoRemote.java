package com.peixeurbano.kraken.interfaces;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.sqlserver.User;

@Remote
public interface UserDaoRemote {

	String getMailscc();

	User getUserByMail(String mail);

}
