package com.fijib.impl.persistence.dao;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

 


 

import com.fijib.impl.persistence.entity.Faq;
import com.fijib.itf.persistence.dao.IFaq;

 
public class FaqHome extends FijibDaoGeneric<Faq> implements IFaq {

 
	public FaqHome(Class<Faq> entityClass) {
		super(Faq.class);
 	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
 		return entityManager;
	}
    
}
