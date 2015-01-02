package com.fijib.web.managedBean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fijib.domain.dto.DtoAgent;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.itf.domain.service.IAgentMetier;
import com.fijib.impl.domain.service.IUtilisateurMetier;

@ManagedBean
@Component
@Scope("session")
public class infoAgentMgdBean implements Serializable{
	@Autowired
	private IUtilisateurMetier iUtilisateurMetier;
	@Autowired
	private IAgentMetier agentMetier;
	private DtoAgent agent;
	private String heureString;
	@PostConstruct
	public void infoAgent(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		 String cin_envoyeur = iUtilisateurMetier.findUserByName(auth.getName()).getCin();
		 agent=agentMetier.getAgentByCin(cin_envoyeur);
		 
			SimpleDateFormat h = new SimpleDateFormat ("hh:mm");		 
			Date currentTime_1 = new Date();		 
	 		heureString = h.format(currentTime_1);
	}
	public DtoAgent getAgent() {
		return agent;
	}
	public void setAgent(DtoAgent agent) {
		this.agent = agent;
 	}
	public String getHeureString() {
		return heureString;
	}
	public void setHeureString(String heureString) {
		this.heureString = heureString;
	}
	
}
