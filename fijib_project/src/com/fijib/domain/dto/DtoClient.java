package com.fijib.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.fijib.impl.persistence.entity.Utilisateur;

public class DtoClient implements Serializable {
	private String cin;
	private Utilisateur utilisateur;
	private String adresseClient;
	private String codePostale;
	private Date dateNaissance;
	private String firstName;
	private String lastName;
	private String numCompteBanc;
	private String tel;
	private String ville;
	private String email;
	public DtoClient(String cin,   String adresseClient,
			String codePostale, Date dateNaissance, String firstName,
			String lastName, String numCompteBanc, String tel, String ville) {
		super();
		this.cin = cin;
		 
		this.adresseClient = adresseClient;
		this.codePostale = codePostale;
		this.dateNaissance = dateNaissance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numCompteBanc = numCompteBanc;
		this.tel = tel;
		this.ville = ville;
	}
	public DtoClient() {
		super();
	}
	public String getCin() {
		return cin;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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
	public String getNumCompteBanc() {
		return numCompteBanc;
	}
	public void setNumCompteBanc(String numCompteBanc) {
		this.numCompteBanc = numCompteBanc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
