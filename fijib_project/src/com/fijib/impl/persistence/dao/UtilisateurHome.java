package com.fijib.impl.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.persistence.dao.IUtilisateur;

@Repository("utilisateurDao")
public class  UtilisateurHome extends FijibDaoGeneric<Utilisateur> implements IUtilisateur {
	@PersistenceContext
	private EntityManager entityManager;
	
	public UtilisateurHome() {
		super( Utilisateur.class);
	}
   
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Utilisateur findUserByName(String email) {
		Query req=entityManager.createQuery("select u from Utilisateur u where u.email like :x");
		req.setParameter("x", email);
		
		if(req.getResultList().size() == 0){
		    return null;
		}else{
			return (Utilisateur) req.getResultList().get(0);
		}
	}
}
