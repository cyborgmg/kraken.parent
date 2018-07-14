package com.peixeurbano.kraken.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.peixeurbano.kraken.entity.redshift.kraken.Mailinglist;

@Remote
public interface MailingListDaoRemote {
	
	public List<Mailinglist> getAll();

}
