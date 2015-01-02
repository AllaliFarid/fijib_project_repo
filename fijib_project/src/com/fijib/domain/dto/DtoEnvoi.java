package com.fijib.domain.dto;

 
import java.util.Date;
 


import com.fijib.impl.persistence.entity.Agence;
import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.impl.persistence.entity.Client;
public class DtoEnvoi implements java.io.Serializable {
 
	 
	 
	 
	private Date dateEnv;
	private double montant=0 ;
	private double frais;
 	private Boolean aunePersonne;
    private String cin_beneficaire;
    private Client envoyeur;
    private Client beneficiare;
    private Agentguichet agentguichet;
    private Agence agence;
    private String code;
    private boolean inscrit; 
	


	public DtoEnvoi(  Date dateEnv, Integer montant,
			double frais,   Boolean aunePersonne) {
	 
 		this.dateEnv = dateEnv;
		this.montant = montant;
		this.frais = frais;
 		this.aunePersonne = aunePersonne;
	}


	public DtoEnvoi() {
		super();
	}

 

	public boolean isInscrit() {
		return inscrit;
	}


	public void setInscrit(boolean inscrit) {
		this.inscrit = inscrit;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Agence getAgence() {
		return agence;
	}


	public void setAgence(Agence agence) {
		this.agence = agence;
	}


	public Date getDateEnv() {
		return dateEnv;
	}


	public void setDateEnv(Date dateEnv) {
		this.dateEnv = dateEnv;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public double getFrais() {
		return frais;
	}


	public void setFrais(double frais) {
		this.frais = frais;
	}


	public Boolean getAunePersonne() {
		return aunePersonne;
	}


	public void setAunePersonne(Boolean aunePersonne) {
		this.aunePersonne = aunePersonne;
	}


	public String getCin_beneficaire() {
		return cin_beneficaire;
	}


	public void setCin_beneficaire(String cin_beneficaire) {
		this.cin_beneficaire = cin_beneficaire;
	}


	public Client getEnvoyeur() {
		return envoyeur;
	}


	public void setEnvoyeur(Client envoyeur) {
		this.envoyeur = envoyeur;
	}


	public Client getBeneficiare() {
		return beneficiare;
	}


	public void setBeneficiare(Client beneficiare) {
		this.beneficiare = beneficiare;
	}


	public Agentguichet getAgentguichet() {
		return agentguichet;
	}


	public void setAgentguichet(Agentguichet agentguichet) {
		this.agentguichet = agentguichet;
	}
    
 
 
}
