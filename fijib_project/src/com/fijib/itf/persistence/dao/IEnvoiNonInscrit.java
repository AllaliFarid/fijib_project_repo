package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;

public interface IEnvoiNonInscrit {
	 void create( EnvoiNonInscrit envoiInscrit);
	    void edit( EnvoiNonInscrit envoiInscrit);
	    void remove( EnvoiNonInscrit envoiInscrit);
	    EnvoiNonInscrit find(Object id);
	    List< EnvoiNonInscrit> findAll();
	    int count();
		public EnvoiNonInscrit getEnvoiNonInscrit(String code);

}
