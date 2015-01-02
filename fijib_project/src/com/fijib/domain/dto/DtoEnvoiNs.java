package com.fijib.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;

public class DtoEnvoiNs implements Serializable {
	private String idEnvoiNs;
	@Min(value= 1, message = "Le montant doit être superieur à 1€")
	private double montant;
	private double frais;
	private String cinBenef;
	private String cinEnv;
	private Date dateEnv;
	private boolean isPaypal;
	private String codeSourceEnvoi;
	
	
	public DtoEnvoiNs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DtoEnvoiNs(String idEnvoiNs, double montant, double frais,
			String cinBenef, String cinEnv, Date dateEnv, boolean isPaypal,
			String codeSourceEnvoi) {
		super();
		this.idEnvoiNs = idEnvoiNs;
		this.montant = montant;
		this.frais = frais;
		this.cinBenef = cinBenef;
		this.cinEnv = cinEnv;
		this.dateEnv = dateEnv;
		this.isPaypal = isPaypal;
		this.codeSourceEnvoi = codeSourceEnvoi;
	}
	public String getIdEnvoiNs() {
		return idEnvoiNs;
	}
	public void setIdEnvoiNs(String idEnvoiNs) {
		this.idEnvoiNs = idEnvoiNs;
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
	public String getCinBenef() {
		return cinBenef;
	}
	public void setCinBenef(String cinBenef) {
		this.cinBenef = cinBenef;
	}
	public String getCinEnv() {
		return cinEnv;
	}
	public void setCinEnv(String cinEnv) {
		this.cinEnv = cinEnv;
	}
	public Date getDateEnv() {
		return dateEnv;
	}
	public void setDateEnv(Date dateEnv) {
		this.dateEnv = dateEnv;
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
