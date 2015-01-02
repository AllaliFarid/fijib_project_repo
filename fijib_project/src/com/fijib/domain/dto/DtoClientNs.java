package com.fijib.domain.dto;

import java.io.Serializable;

public class DtoClientNs implements Serializable{
	private String cin;
	private String nom;
	private String prenom;
	/**
	 * 
	 */
	public DtoClientNs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}	
}
