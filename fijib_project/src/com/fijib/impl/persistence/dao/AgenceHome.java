package com.fijib.impl.persistence.dao;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

 


import com.fijib.impl.persistence.entity.Agence;
import com.fijib.itf.persistence.dao.IAgence;
 
public class AgenceHome extends FijibDaoGeneric<Agence> implements IAgence {

 

	@PersistenceContext
	private EntityManager entityManager;
	
	public AgenceHome() {
		super( Agence.class);
	}
   
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
