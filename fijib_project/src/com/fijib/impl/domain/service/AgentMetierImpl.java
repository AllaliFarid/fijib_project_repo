package com.fijib.impl.domain.service;

import org.apache.log4j.jmx.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoAgent;
import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.itf.domain.service.IAgentMetier;
import com.fijib.itf.persistence.dao.IAgentGuichet;
 

@Service("iagentMetier")
@Transactional
public class AgentMetierImpl  implements IAgentMetier{

	@Autowired
	IAgentGuichet IagentGuichet;

	@Override
	public DtoAgent getAgentByCin(String cin) {
		Agentguichet agentguichet=IagentGuichet.find(cin);
		DtoAgent agent=new DtoAgent();
		agent.setCin(agentguichet.getCin());
		agent.setDateRecrutement(agentguichet.getDateRecrutement());
		agent.setEmail(agentguichet.getEmail());
		agent.setFirstName(agentguichet.getFirstName());
		agent.setLastName(agentguichet.getLastName());
		agent.setAgence(agentguichet.getAgence());
		return agent;
	}

	@Override
	public Agentguichet getAgentByCIN_entity(String cin) {
	 
		return IagentGuichet.find(cin);
	}
    

 
}