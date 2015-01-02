package com.fijib.web.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoListprefere;
import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.domain.service.IClientMetier;
import com.fijib.itf.domain.service.IlistePrefereMetier;
import com.fijib.itf.domain.service.RechercheService;

@ManagedBean(name="listePrefereMdgbean")
@Component
@Scope("session")
public class ListePrefereMdgbean implements Serializable {
	
	@Autowired
	private IUtilisateurMetier iUtilisateurMetier;
	@Autowired
	private IlistePrefereMetier ilistePrefereMetier;
	@Autowired
	private IClientMetier clientMetier;
	@Autowired
	private RechercheService rechercheService;
	
	private DtoListprefere dtoListprefere;
	private List<DtoClient> clients;
	private List<DtoClient> clientsRecherche;
	private String typeRecherche;
	private String inputRecherche;
	private boolean etatRech = false;
	private Client thisClient;

	
	public void getListePrefere() {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		if(auth != null){
			
			Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
			
			thisClient = clientMetier.getClient(user.getCin());
			
			thisClient.setUtilisateur(user);
			
			clients = ilistePrefereMetier.getListPrefere(clientMetier
					.getClient((user.getCin())));
		}
	}
	
	public void ajouterPage() {
		this.etatRech = true;
		System.out.println(this.etatRech);
	}
	
	public void recherche(){
		if(typeRecherche.equalsIgnoreCase("email") && !thisClient.getUtilisateur().getEmail().equalsIgnoreCase(inputRecherche)
				&& !this.inputRecherche.equalsIgnoreCase("")){
			clientsRecherche = rechercheService.getListRechercheEmail(inputRecherche);
		}
		else if(typeRecherche.equalsIgnoreCase("nom") && !thisClient.getLastName().equalsIgnoreCase(inputRecherche)
				&& !this.inputRecherche.equalsIgnoreCase("")){
			clientsRecherche = rechercheService.getListRechercheNom(inputRecherche);
		}
		else if(typeRecherche.equalsIgnoreCase("cin") && !thisClient.getCin().equalsIgnoreCase(inputRecherche)
				&& !this.inputRecherche.equalsIgnoreCase("")){
			clientsRecherche = rechercheService.getListRechercheCin(inputRecherche);
		}
	}
	
	public void affich(){
		System.out.println(clients.size());
	}

	public void selectClientAjoutFromDialog(DtoClient client) {
		int j = 0;
		Iterator<DtoClient> i = clients.iterator();
		while(i.hasNext()){
			DtoClient cli = (DtoClient) i.next();
			
			if(cli.getCin().equalsIgnoreCase(client.getCin())){
				j++;
			}
		}
		
		if(j == 0){
			clients.add(client);
			
			rechercheService.saveBenefPrefere(client, thisClient);
		}
		
		this.etatRech = false;
	}
	
	public void supprimer(DtoClient client){
		rechercheService.supprimerPrefere(client.getCin(), thisClient);
		clients.remove(client);
	}
	
	public void selectClientFromDialog(DtoClient client) {
		this.etatRech = false;
		RequestContext.getCurrentInstance().closeDialog(client);
	}

	public List<DtoClient> getClients() {
		return clients;
	}

	public void setClients(List<DtoClient> clients) {
		this.clients = clients;
	}

	public DtoListprefere getDtoListprefere() {
		return dtoListprefere;
	}

	public void setDtoListprefere(DtoListprefere dtoListprefere) {
		this.dtoListprefere = dtoListprefere;
	}

	public String getTypeRecherche() {
		return typeRecherche;
	}

	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	public String getInputRecherche() {
		return inputRecherche;
	}

	public void setInputRecherche(String inputRecherche) {
		this.inputRecherche = inputRecherche;
	}

	public List<DtoClient> getClientsRecherche() {
		return clientsRecherche;
	}

	public void setClientsRecherche(List<DtoClient> clientsRecherche) {
		this.clientsRecherche = clientsRecherche;
	}

	public boolean isEtatRech() {
		return etatRech;
	}

	public void setEtatRech(boolean etatRech) {
		this.etatRech = etatRech;
	}
}
