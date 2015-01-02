package com.fijib.domain.dto;

import java.util.Date;

import com.fijib.impl.persistence.entity.Agence;
import com.fijib.impl.persistence.entity.Utilisateur;

public class DtoAgent {
	private String cin;
	private Utilisateur utilisateur;
	private Agence agence;
	private Date dateRecrutement;
	private String email;
	private String firstName;
	private String lastName;
	
	
	
	public DtoAgent() {
		super();
	}
	public DtoAgent(String cin, Utilisateur utilisateur, Agence agence,
			Date dateRecrutement, String email, String firstName,
			String lastName) {
		super();
		this.cin = cin;
		this.utilisateur = utilisateur;
		this.agence = agence;
		this.dateRecrutement = dateRecrutement;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public Date getDateRecrutement() {
		return dateRecrutement;
	}
	public void setDateRecrutement(Date dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
