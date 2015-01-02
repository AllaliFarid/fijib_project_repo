package com.fijib.domain.dto;


public class DtoRecu implements java.io.Serializable {
	
	private String codeEnvoi;
	private String DateLe;
	private String DateTransfert;
	private String intitule;
	private String cinBenef;
	private String cinEnv;
	private String typePaiment;
	private String codeVirement;
	private double montant;
	private double frais;
	private double total;
	
	
	public DtoRecu(String codeEnvoi, String dateLe, String dateTransfert,
			String intitule, String cinBenef, String cinEnv,
			String typePaiment,String codeVirement , double montant, double frais, double total) {
		super();
		this.codeEnvoi = codeEnvoi;
		DateLe = dateLe;
		DateTransfert = dateTransfert;
		this.intitule = intitule;
		this.cinBenef = cinBenef;
		this.cinEnv = cinEnv;
		this.typePaiment = typePaiment;
		this.codeVirement = codeVirement;
		this.montant = montant;
		this.frais = frais;
		this.total = total;
	}



	public DtoRecu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getCodeVirement() {
		return codeVirement;
	}

	public void setCodeVirement(String codeVirement) {
		this.codeVirement = codeVirement;
	}

	public String getCodeEnvoi() {
		return codeEnvoi;
	}
	public void setCodeEnvoi(String codeEnvoi) {
		this.codeEnvoi = codeEnvoi;
	}
	public String getDateLe() {
		return DateLe;
	}
	public void setDateLe(String dateLe) {
		DateLe = dateLe;
	}
	public String getDateTransfert() {
		return DateTransfert;
	}
	public void setDateTransfert(String dateTransfert) {
		DateTransfert = dateTransfert;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
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
	public String getTypePaiment() {
		return typePaiment;
	}
	public void setTypePaiment(String typePaiment) {
		this.typePaiment = typePaiment;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
