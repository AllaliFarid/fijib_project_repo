package com.fijib.impl.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fijib.impl.persistence.entity.Statistiquegenerale;
import com.fijib.itf.persistence.dao.IStatistiquegenerale;

public class StatistiquegeneraleHome extends FijibDaoGeneric<Statistiquegenerale> implements IStatistiquegenerale  {

	public StatistiquegeneraleHome(Class<Statistiquegenerale> entityClass) {
		super(Statistiquegenerale.class);
 	}
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
 		return entityManager;
	}

}
