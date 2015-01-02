package com.fijib.web.managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fijib.domain.dto.DtoInscriptionClient;
import com.fijib.itf.domain.service.InscriptionMetier;

@ManagedBean(name="inscriptionBean")
@RequestScoped
@Component
public class InscriptionBean implements Serializable {
	
	@Autowired
	private transient InscriptionMetier inscriptionMetier;
    
	private DtoInscriptionClient infoClient;
    
	public String ajouterClient() {
		
		if(inscriptionMetier.verifierClient(this.infoClient.getCin(), this.infoClient.getEmail())==false){
			inscriptionMetier.inscrire(this.infoClient, 0);
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Votre Inscription est effectuée !"));
			initInfoClient();
			return null;
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					 new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ce Client déja existant !"));
			 return null;
		}
	}
	
	public void initInfoClient(){
		infoClient.setCin(null);
		infoClient.setAdresseClient(null);
		infoClient.setCodePostale(null);
		infoClient.setDateNaissance(null);
		infoClient.setEmail(null);
		infoClient.setFirstName(null);
		infoClient.setLastName(null);
		infoClient.setNumCompteBanc(null);
		infoClient.setPassword(null);
		infoClient.setTel(null);
		infoClient.setVille(null);
	}
   	
	public InscriptionBean() {
		this.infoClient = new DtoInscriptionClient();
	}
	
	public DtoInscriptionClient getInfoClient() {
		return infoClient;
	}

	public void setInfoClient(DtoInscriptionClient infoClient) {
		this.infoClient = infoClient;
	}

	public void setInscriptionMetier(InscriptionMetier inscriptionMetier) {
		this.inscriptionMetier = inscriptionMetier;
	}
}
