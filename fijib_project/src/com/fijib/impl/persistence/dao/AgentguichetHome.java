package com.fijib.impl.persistence.dao;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 


 



import org.springframework.stereotype.Repository;

import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.itf.persistence.dao.IAgentGuichet;
@Repository("agentDao")
public class AgentguichetHome extends FijibDaoGeneric<Agentguichet> implements IAgentGuichet{


	 
		@PersistenceContext
		private EntityManager entityManager;
		
		public AgentguichetHome() {
			super( Agentguichet.class);
		}
	   
		public EntityManager getEntityManager() {
			return entityManager;
		}
    
}
