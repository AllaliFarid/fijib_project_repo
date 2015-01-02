package com.fijib.itf.persistence.dao;

import java.util.List;

 import com.fijib.impl.persistence.entity.Role;

public interface IRole {
	    void create( Role role);
	    void edit( Role role);
	    void remove( Role role);
	    Role find(Object id);
	    List< Role> findAll();
	    int count();
 }
