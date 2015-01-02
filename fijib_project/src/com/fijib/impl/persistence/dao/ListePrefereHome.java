package com.fijib.impl.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Listeprefere;
import com.fijib.itf.persistence.dao.IListePrefere;

@Repository 
public class ListePrefereHome extends FijibDaoGeneric<Listeprefere> implements IListePrefere {

	public ListePrefereHome() {
		super(Listeprefere.class);
		// TODO Auto-generated constructor stub
	}
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	protected EntityManager getEntityManager() {
 		return entityManager;
	}
	@Override
	public List<Listeprefere> getListePreferer(Client client) {
		Query req=entityManager.createQuery("select l from Listeprefere l where l.clientByCliCinEnv like :x");
		req.setParameter("x", client);
		return  req.getResultList();
	}



}
