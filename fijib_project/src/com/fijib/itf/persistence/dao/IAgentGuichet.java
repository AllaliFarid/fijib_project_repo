package com.fijib.itf.persistence.dao;

import java.util.List;

import com.fijib.impl.persistence.entity.Agentguichet;

 
public interface IAgentGuichet {
	 void create( Agentguichet agentGuichet);
	    void edit( Agentguichet agentGuichet);
	    void remove( Agentguichet agentGuichet);
	    Agentguichet find(Object id);
	    List< Agentguichet> findAll();
	    int count();
}
