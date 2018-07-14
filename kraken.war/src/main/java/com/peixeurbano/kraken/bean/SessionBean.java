package com.peixeurbano.kraken.bean;

import javax.annotation.ManagedBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.peixeurbano.kraken.entity.abstracts.AbstractSession;

@ManagedBean
@Named("sessionBean")
@SessionScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class SessionBean extends AbstractSession {}
