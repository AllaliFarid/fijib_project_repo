package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Administrateur;
 
public interface IAdmministrateur {
	    void create( Administrateur adminstrateur);
	    void edit( Administrateur adminstrateur);
	    void remove( Administrateur adminstrateur);
	    Administrateur find(Object id);
	    List< Administrateur> findAll();
	    int count();
	    Administrateur findUserByName(String username);
}
