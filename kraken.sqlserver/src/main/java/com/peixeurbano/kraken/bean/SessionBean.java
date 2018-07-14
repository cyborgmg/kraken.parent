package com.peixeurbano.kraken.bean;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.SessionScoped;

import com.peixeurbano.kraken.entity.abstracts.AbstractSession;

@SessionScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class SessionBean extends AbstractSession {}
