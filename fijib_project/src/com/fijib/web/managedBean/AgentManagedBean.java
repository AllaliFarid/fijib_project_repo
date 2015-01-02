package com.fijib.web.managedBean;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoEnvoi;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.itf.domain.service.CalculFrais;
import com.fijib.itf.domain.service.IAgentMetier;
import com.fijib.itf.domain.service.IClientMetier;
import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.itf.domain.service.InscriptionAgentClientMetier;

@ManagedBean
@Component
@RequestScoped
public class AgentManagedBean {

	@Autowired
	private IClientMetier clientMetier;
	@Autowired
	private InscriptionAgentClientMetier agentClientMetier;
	@Autowired
	private IEnvoiMetier envoiMetier;
	@Autowired
	IUtilisateurMetier iUtilisateurMetier;
	@Autowired
	private IAgentMetier agentMetieritf;
	@Autowired
	private CalculFrais calculFrais;
	  
	
	private DtoClient client=new DtoClient()  ;
	private boolean etatform1=false;
	private boolean etatform2=false;
	private DtoEnvoi envoiDto=new DtoEnvoi();
	private String date ;
	private String agence;
	private String intitule;
	private String codeTransfert;
	private boolean etatRecu;
	public void init(){
 		  client=null ;
		  etatform1=false;
	      etatform2=false;
	      etatRecu=false;
	  
	}
	@PostConstruct
	public void info(){
 		 		Date currentTime_1 = new Date();		  		 		
 				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
				  date = formater.format(currentTime_1);
		 
	}
	
	public String getClientByCin(){
  
 	  if(clientMetier.getClientDto(client.getCin())== null) {
 		  client	=new DtoClient(null, null,null,null,null,null,null,null,null); 
  		  etatform1=true; etatform2=false;
	      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "le client n'est pas encors inscrit veuillez l'inscrire svp !."));
	      return  null ;
	}else{
		  client=clientMetier.getClientDto(client.getCin());
		  etatform2=true;etatform1=false;
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "le client est inscrit."));
		  return  "securePages/AgentPages/accueilAgent" ;
	}
 	}
	
    public List<String> completeText(String query) {
    	
         List<String> results = new ArrayList<String>();
        List<Client> clients=new ArrayList<>();
        clients  =   clientMetier.getClientDebutCin(query);
        for(Client c:clients) {
            results.add(c.getCin());
        }
        return results;
    }
    
    public void inscrire(){
    	 
    	agentClientMetier.inscrire(client);
     	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "le client est inscrit avec succès."));
    	etatform2=true;etatform1=false;
    }
    
    public void envoyer(){
      FacesContext context = FacesContext.getCurrentInstance();                
           
         		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
                String cin_agent = iUtilisateurMetier.findUserByName(auth.getName()).getCin();
                envoiDto.setAgentguichet(agentMetieritf.getAgentByCIN_entity(cin_agent));
                envoiDto.setBeneficiare(clientMetier.getClient(envoiDto.getCin_beneficaire()));
                envoiDto.setEnvoyeur(clientMetier.getClient(client.getCin()));
                envoiDto.setAgence(agentMetieritf.getAgentByCIN_entity(cin_agent).getAgence());;
                codeTransfert=envoiMetier.envoyer(envoiDto);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "l'envoi est terminé avec succès.") );
                etatRecu=true;
                etatform1=false;
      	        etatform2=false;
      			
        }
    
    public void createReçuPDF() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
     	String url = "http://localhost:8080/fijib_project/reçuEnvoi.xhtml";
     	agence= envoiDto.getAgence().getAgenceName();
 		intitule=client.getFirstName()+" "+ client.getLastName();
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

    public void calculFrais(){
    	envoiDto.setFrais(calculFrais.calculerFrais(envoiDto.getMontant(), "VIREMENT"));
    	 
    }

 
	public boolean isEtatRecu() {
		return etatRecu;
	}
	public void setEtatRecu(boolean etatRecu) {
		this.etatRecu = etatRecu;
	}
	public String getCodeTransfert() {
		return codeTransfert;
	}
	public void setCodeTransfert(String codeTransfert) {
		this.codeTransfert = codeTransfert;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DtoClient getClient() {
		return client;
	}
	public void setClient(DtoClient client) {
		this.client = client;
	}

	 

	public boolean isEtatform1() {
		return etatform1;
	}

	public void setEtatform1(boolean etatform1) {
		this.etatform1 = etatform1;
	}

	public boolean isEtatform2() {
		return etatform2;
	}

	public void setEtatform2(boolean etatform2) {
		this.etatform2 = etatform2;
	}

	public DtoEnvoi getEnvoiDto() {
		return envoiDto;
	}

	public void setEnvoiDto(DtoEnvoi envoiDto) {
		this.envoiDto = envoiDto;
	}
	public String getAgence() {
		return agence;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

  

 
 
	
	
}
