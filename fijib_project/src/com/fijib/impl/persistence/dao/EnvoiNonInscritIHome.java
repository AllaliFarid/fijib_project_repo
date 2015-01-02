package com.fijib.impl.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

 import com.fijib.impl.persistence.entity.EnvoiNonInscrit;
import com.fijib.itf.persistence.dao.IEnvoiNonInscrit;

@Repository("envoiNonInscritDao")
public class EnvoiNonInscritIHome  extends FijibDaoGeneric<EnvoiNonInscrit> implements IEnvoiNonInscrit   {
	
	

	@PersistenceContext
	EntityManager entityManager;
	
	public EnvoiNonInscritIHome() {
		super(EnvoiNonInscrit.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
    return entityManager;
	}

	@Override
	public EnvoiNonInscrit getEnvoiNonInscrit(String code) {
		Query req=entityManager.createQuery("select e from EnvoiNonInscrit  e where e.idEnvoiNs like :x");
		req.setParameter("x", code);
		if(req.getResultList().size()!=0)
		return (EnvoiNonInscrit) req.getResultList().get(0);	
		return null;
		 
	}
}
