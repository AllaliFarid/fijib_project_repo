package com.fijib.itf.persistence.dao;

import java.util.List;

 import com.fijib.impl.persistence.entity.Statistiquegenerale;

public interface IStatistiquegenerale {
	 void create( Statistiquegenerale statistiquegenerale);
	    void edit( Statistiquegenerale statistiquegenerale);
	    void remove( Statistiquegenerale statistiquegenerale);
	    Statistiquegenerale find(Object id);
	    List< Statistiquegenerale> findAll();
	    int count();
}
