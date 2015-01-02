package com.fijib.impl.persistence.dao;

 


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.itf.persistence.dao.IClient;
 
 @Repository("clientDao")
 public class ClientHome extends FijibDaoGeneric<Client> implements IClient, Serializable {
    
		@PersistenceContext
		private EntityManager entityManager;
		
		public ClientHome() {
			super(Client.class);
			// TODO Auto-generated constructor stub
		}



		public EntityManager getEntityManager() {
			return entityManager;
		}

		@Override
		public List<Client> getClientDebutCin(String cin) {
			Query req=entityManager.createQuery("select c from Client c where c.cin like :x");
			req.setParameter("x", cin+"%");
			return req.getResultList();	
		}
         
		
		public List<Client> getClientParNom(String nom) {
			Query req=entityManager.createQuery("select c from Client c where c.lastName like :x");
			req.setParameter("x", nom+"%");
			return req.getResultList();	
		}
 
 
}
