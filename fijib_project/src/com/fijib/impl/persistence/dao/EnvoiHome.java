package com.fijib.impl.persistence.dao;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.itf.persistence.dao.IEnvoi;

@Repository("envoiInsDao")
public class EnvoiHome extends FijibDaoGeneric<EnvoiInscrit> implements IEnvoi{
	
	public EnvoiHome() {
		super(EnvoiInscrit.class);
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
 		return entityManager;
	}
    
	@Override
	public EnvoiInscrit getEnvoiInscrit(String code) {
		Query req=entityManager.createQuery("select e from EnvoiInscrit  e where e.envoiId like :x");
		req.setParameter("x", code);
		if(req.getResultList().size()!=0)
		return (EnvoiInscrit) req.getResultList().get(0);
		else
		return null;
	}

	@Override
	public List<EnvoiInscrit> getListEnvoi(String cin) {
		Query req=entityManager.createQuery("select e from EnvoiInscrit  e where e.clientByCliCinEnv.cin like :x");
		req.setParameter("x", cin);
		return req.getResultList();
	}

	@Override
	public int[] historiqueEnvoi() {
		int[] res=new int[13];
		String res1=new String();
	 
 	 	for(int i=0;i<=12;i++){
	 		javax.persistence.Query req=entityManager.createNativeQuery("select count(EnvoiID) from envoi_inscrit where DateEnv between ? and ?");
	 		 
 	 		req.setParameter( 1, "2014-"+i+"-00");
 			 
	 		req.setParameter( 2 ,"2014-"+i+"-30" );
 		 
 		   res[i] = ((BigInteger) req.getResultList().get(0)).intValue();
 			  
  	 	}
	 return res;
	}
}
