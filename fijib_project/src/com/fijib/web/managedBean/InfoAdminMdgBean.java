package com.fijib.web.managedBean;

import java.text.SimpleDateFormat;
import java.util.Date;

 




import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

 import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.itf.domain.service.IAdminMetier;
 

@ManagedBean
@Component
@Scope("session")
public class InfoAdminMdgBean {
	@Autowired
	private IUtilisateurMetier iUtilisateurMetier;
	@Autowired
	private IAdminMetier adminMetier;
	private Administrateur administrateur; 
	private String heureString;
	 
	@PostConstruct
	public void infoAgent(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		 String cin_Admin = iUtilisateurMetier.findUserByName(auth.getName()).getCin();
		 administrateur=adminMetier.getAdmin(cin_Admin);
 			SimpleDateFormat h = new SimpleDateFormat ("hh:mm");		 
			Date currentTime_1 = new Date();		 
	 		heureString = h.format(currentTime_1);
	}
 
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public String getHeureString() {
		return heureString;
	}
	public void setHeureString(String heureString) {
		this.heureString = heureString;
	}
}
