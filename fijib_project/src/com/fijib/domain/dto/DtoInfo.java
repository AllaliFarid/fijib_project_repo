package com.fijib.domain.dto;

import java.io.Serializable;

public class DtoInfo implements Serializable{
	
	private String nomPrenom;
	private String compte;
	private String dateLocal;
	
	public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	public String getCompte() {
		return compte;
	}
	public void setCompte(String compte) {
		this.compte = compte;
	}
	public String getDateLocal() {
		return dateLocal;
	}
	public void setDateLocal(String dateLocal) {
		this.dateLocal = dateLocal;
	}
	
	public DtoInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
}
