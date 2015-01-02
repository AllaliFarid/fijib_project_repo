package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Client;

 
public interface IClient {
	 void create( Client client);
	    void edit( Client client);
	    void remove( Client client);
	    Client find(Object id);
	    List< Client> findAll();
	    int count();
	    List<Client> getClientDebutCin(String cin);
	    public List<Client> getClientParNom(String nom);
 }
