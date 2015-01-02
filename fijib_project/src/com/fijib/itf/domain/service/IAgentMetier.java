package com.fijib.itf.domain.service;

import org.apache.log4j.jmx.Agent;

import com.fijib.domain.dto.DtoAgent;
import com.fijib.impl.persistence.entity.Agentguichet;

public interface IAgentMetier {
    public DtoAgent getAgentByCin(String cin );
    public Agentguichet getAgentByCIN_entity(String cin);
}
