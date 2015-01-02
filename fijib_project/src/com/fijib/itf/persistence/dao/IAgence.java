package com.fijib.itf.persistence.dao;

import java.util.List;

 import com.fijib.impl.persistence.entity.Agence;

public interface IAgence {
    void create( Agence agence);
    void edit( Agence agence);
    void remove( Agence agence);
    Agence find(Object id);
    List< Agence> findAll();
    int count();
}
