package com.fijib.impl.persistence.dao; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.itf.persistence.dao.IAdmministrateur;
@Repository 
public class AdministrateurHome extends FijibDaoGeneric<Administrateur> implements IAdmministrateur {
   
	
 
	@PersistenceContext
	private EntityManager entityManager;
	
	public AdministrateurHome() {
		super( Administrateur.class);
	}
   
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Administrateur findUserByName(String username) {
		Query req=entityManager.createQuery("select a from Administrateur a where a.cin like :x");
		req.setParameter("x", username);
		return (Administrateur) req.getResultList().get(0);		
	}
  
}
