package com.fijib.web.managedBean;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoEnvoi;
import com.fijib.domain.dto.DtoRetrait;
import com.fijib.impl.persistence.entity.Agence;
import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.Retrait;
import com.fijib.itf.domain.service.IAgentMetier;
import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.itf.domain.service.IRetraitMetier;
import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.itf.domain.service.InscriptionAgentClientMetier;

@Component
@ManagedBean
@SessionScoped
public class RetraitMgbBean {

	@Autowired
	private IRetraitMetier  iRetraitMetier;
	@Autowired
	private IEnvoiMetier envoiMetier;
	@Autowired
	private InscriptionAgentClientMetier agentClientMetier;
	@Autowired private IUtilisateurMetier iUtilisateurMetier;
	@Autowired private IAgentMetier agentMetieritf;
	private DtoEnvoi dtoEnvoi=new DtoEnvoi();
	private DtoClient  client=new DtoClient();
	private Agentguichet agentguichet;
	 
	private String dateRetrait;
	private String intitule;
	private boolean etatform=false;
	private boolean etatRecu=false;
	private boolean etatFormInsc=false;
	public String getEnvoi(){
		DtoEnvoi dtoEnvoiserv=envoiMetier.getEnvoi(dtoEnvoi.getCode());
		
		if (dtoEnvoiserv!=null) {
	     	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "le code est valide."));
	     	dtoEnvoi=dtoEnvoiserv;	     	
	     	if(dtoEnvoiserv.isInscrit())
	     		etatform=true;
	     	else {etatFormInsc=true;
	     	client.setCin(dtoEnvoi.getCin_beneficaire());
	     	}
	     		
		}else{
	     	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "le code n'est pas valide."));
	     	etatform=false;
	     	
		}
		return null;
	}
	public void validerRetrait(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
        String cin_agent = iUtilisateurMetier.findUserByName(auth.getName()).getCin();
          agentguichet= agentMetieritf.getAgentByCIN_entity(cin_agent);
		if(dtoEnvoi.isInscrit()){
		   
           Retrait retrait=new Retrait(envoiMetier.getEnvoiInsEntity(dtoEnvoi.getCode()), null, agentguichet,new Date(), dtoEnvoi.getMontant());
		   iRetraitMetier.enregistrerRetrait(retrait);
		   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "le retrait est terminé avec succès."));
		   etatform=false;
		   etatRecu=true;
		}
		else{
		   Retrait retrait=new Retrait(null, envoiMetier.getEnvoiNonInsEntity(dtoEnvoi.getCode()), agentguichet,new Date(), dtoEnvoi.getMontant());
		   iRetraitMetier.enregistrerRetrait(retrait);
		   etatform=false;
		   etatRecu=true;}
			
	}
	   public void inscrire(){
	    	 
	    	agentClientMetier.inscrire(client);
	     	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "le client est inscrit avec succès."));
	    	etatFormInsc=false; etatform=true;
	    	  dtoEnvoi=envoiMetier.getEnvoi(dtoEnvoi.getCode());
	    }
	   public void createReçuPDF() {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
	     	String url = "http://localhost:8080/fijib_project/reçuRetrait.xhtml";
	     	Date currentTime_1 = new Date();		  		 		
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");	 
		 		dateRetrait = formater.format(currentTime_1);
	 		 intitule=dtoEnvoi.getBeneficiare().getFirstName()+" "+ dtoEnvoi.getBeneficiare().getLastName();
	 		  
	 		etatRecu=false;
	 		try {
	 
				ITextRenderer renderer = new ITextRenderer();
				renderer.setDocument(url);
	 			renderer.layout();
				HttpServletResponse response = (HttpServletResponse) externalContext
						.getResponse();
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "C://user//first.pdf");
				OutputStream browserStream = response.getOutputStream();
				renderer.createPDF(browserStream);
				browserStream.close();
	 		} catch (Exception ex) {
				ex.printStackTrace();
			}
			facesContext.responseComplete();
		}
	 
 
	public Agentguichet getAgentguichet() {
		return agentguichet;
	}
	public void setAgentguichet(Agentguichet agentguichet) {
		this.agentguichet = agentguichet;
	}
 
	public String getDateRetrait() {
		return dateRetrait;
	}
	public void setDateRetrait(String dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public DtoClient getClient() {
		return client;
	}
	public void setClient(DtoClient client) {
		this.client = client;
	}
	public boolean isEtatFormInsc() {
		return etatFormInsc;
	}
	public void setEtatFormInsc(boolean etatFormInsc) {
		this.etatFormInsc = etatFormInsc;
	}
	public DtoEnvoi getDtoEnvoi() {
		return dtoEnvoi;
	}

	public void setDtoEnvoi(DtoEnvoi dtoEnvoi) {
		this.dtoEnvoi = dtoEnvoi;
	}


	public boolean isEtatform() {
		return etatform;
	}


	public void setEtatform(boolean etatform) {
		this.etatform = etatform;
	}
	public boolean isEtatRecu() {
		return etatRecu;
	}
	public void setEtatRecu(boolean etatRecu) {
		this.etatRecu = etatRecu;
	}
	
}
