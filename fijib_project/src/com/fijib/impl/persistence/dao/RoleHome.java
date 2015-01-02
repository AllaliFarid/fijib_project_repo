package com.fijib.impl.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.Role;
import com.fijib.itf.persistence.dao.IRole;

@Repository("roleDao")
public class RoleHome extends FijibDaoGeneric<Role> implements IRole{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public RoleHome() {
		super( Role.class);
	}
   
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
