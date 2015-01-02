package com.fijib.domain.dto;

 
import java.util.Date;
 



import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.impl.persistence.entity.Client;
public class DtoEnvoiClient implements java.io.Serializable {

	private String envoiId;	 
	private Date dateEnv;
	private double montant = 0 ;
	private double frais;
 	private Boolean aunePersonne;
    private String cin_beneficaire;
    private Client envoyeur;
    private Client beneficiare;
    private Agentguichet agentguichet;
    private boolean isPaypal;
	private String codeSourceEnvoi;
	
	public DtoEnvoiClient() {
		super();
	}
	
	public DtoEnvoiClient(String envoiId, Date dateEnv, double montant, double frais,
			Boolean aunePersonne, String cin_beneficaire, boolean isPaypal,
			String codeSourceEnvoi) {
		super();
		this.envoiId = envoiId;
		this.dateEnv = dateEnv;
		this.montant = montant;
		this.frais = frais;
		this.aunePersonne = aunePersonne;
		this.cin_beneficaire = cin_beneficaire;
		this.isPaypal = isPaypal;
		this.codeSourceEnvoi = codeSourceEnvoi;
	}



	public String getEnvoiId() {
		return envoiId;
	}


	public void setEnvoiId(String envoiId) {
		this.envoiId = envoiId;
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


	public boolean isPaypal() {
		return isPaypal;
	}


	public void setPaypal(boolean isPaypal) {
		this.isPaypal = isPaypal;
	}


	public String getCodeSourceEnvoi() {
		return codeSourceEnvoi;
	}


	public void setCodeSourceEnvoi(String codeSourceEnvoi) {
		this.codeSourceEnvoi = codeSourceEnvoi;
	}
}
