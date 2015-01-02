package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.EnvoiInscrit;

public interface IEnvoi {
	 	void create( EnvoiInscrit envoi);
	    void edit( EnvoiInscrit envoi);
	    void remove( EnvoiInscrit envoi);
	    EnvoiInscrit find(Object id);
	    List< EnvoiInscrit> findAll();
	    int count();
	    public EnvoiInscrit getEnvoiInscrit(String code);
	    public List<EnvoiInscrit> getListEnvoi(String cin);
	    public int[] historiqueEnvoi();
}
