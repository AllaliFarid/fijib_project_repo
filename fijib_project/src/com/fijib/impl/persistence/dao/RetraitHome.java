package com.fijib.impl.persistence.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.Retrait;
import com.fijib.itf.persistence.dao.IRetrait;
 @Repository
public class RetraitHome extends FijibDaoGeneric<Retrait> implements IRetrait {

	
	@PersistenceContext
	EntityManager entityManager;
	public RetraitHome() {
		super(Retrait.class);
	 
	}
 

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public List<Retrait> getListREtrait(String cin) {
		Query req=entityManager.createQuery("select r from Retrait  r where r.envoiInscrit.clientByCinBene.cin like :x");
		req.setParameter("x", cin);
		return req.getResultList();
	}


	@Override
	public int[] historiqueRetrait() {
		
		int[] res=new int[13];
		String res1=new String();
	 
 	 	for(int i=0;i<=12;i++){
	 		javax.persistence.Query req=entityManager.createNativeQuery("select count(RetraitId) from retrait where DateRetrait between ? and ?");
	 		 
 	 		req.setParameter( 1, "2014-"+i+"-00");
 			 
	 		req.setParameter( 2 ,"2014-"+i+"-30" );
 		 
 		   res[i] = ((BigInteger) req.getResultList().get(0)).intValue();
 			  
  	 	}
	 return res;
	}

 

}
