package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Listeprefere;

public interface IListePrefere {
	 void create( Listeprefere listeprefere);
	    void edit( Listeprefere listeprefere);
	    void remove( Listeprefere listeprefere);
	    Listeprefere find(Object id);
	    List< Listeprefere> findAll();
	    int count();
	    List<Listeprefere> getListePreferer(Client client);
}
