package com.fijib.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

public class DtoInscriptionClient implements Serializable {
	private String cin;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String adresseClient;
	private String tel;
	private String ville;
	private String codePostale;
	private Date dateNaissance;
	private String numCompteBanc;
	
	public DtoInscriptionClient(String cin, String firstName, String lastName,
			String login, String password, String confirmedPassword,
			String email, String adresseClient, String tel, String ville,
			String codePostale, Date dateNaissance, String numCompteBanc) {
		super();
		this.cin = cin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.adresseClient = adresseClient;
		this.tel = tel;
		this.ville = ville;
		this.codePostale = codePostale;
		this.dateNaissance = dateNaissance;
		this.numCompteBanc = numCompteBanc;
	}


	public DtoInscriptionClient() {
		super();
		
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
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

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdresseClient() {
		return adresseClient;
	}


	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
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


	public String getNumCompteBanc() {
		return numCompteBanc;
	}


	public void setNumCompteBanc(String numCompteBanc) {
		this.numCompteBanc = numCompteBanc;
	}
	
}
