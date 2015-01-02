package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Utilisateur;

public interface IUtilisateur {
	    void create( Utilisateur utilisateur);
	    void edit( Utilisateur utilisateur);
	    void remove( Utilisateur utilisateur);
	    Utilisateur find(Object id);
	    List< Utilisateur> findAll();
	    int count();
	    public Utilisateur findUserByName(String login);
}
