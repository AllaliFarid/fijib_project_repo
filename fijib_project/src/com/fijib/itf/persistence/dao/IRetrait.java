package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Retrait;

public interface IRetrait  {
	 void create( Retrait retrait);
	    void edit( Retrait retrait);
	    void remove( Retrait retrait);
	    Retrait find(Object id);
	    List< Retrait> findAll();
	    int count();
	     List<Retrait> getListREtrait(String cin);
		int[] historiqueRetrait();
}
