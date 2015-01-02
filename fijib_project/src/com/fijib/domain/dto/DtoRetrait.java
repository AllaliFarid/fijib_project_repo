package com.fijib.domain.dto;

import java.util.Date;

import com.fijib.impl.persistence.entity.Agentguichet;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;

public class DtoRetrait {
  
	
	private Integer retraitId;
	private EnvoiInscrit envoiInscrit;
	private EnvoiNonInscrit envoiNonInscrit;
	private Agentguichet agentguichet;
	private Date dateRetrait;
	private double montant;
	
	public DtoRetrait(  EnvoiInscrit envoiInscrit,
			EnvoiNonInscrit envoiNonInscrit, Agentguichet agentguichet,
			Date dateRetrait, double montant) {
		super();
	 
		this.envoiInscrit = envoiInscrit;
		this.envoiNonInscrit = envoiNonInscrit;
		this.agentguichet = agentguichet;
		this.dateRetrait = dateRetrait;
		this.montant = montant;
	}
	public DtoRetrait() {
		super();
	}
	
 
	public Integer getRetraitId() {
		return retraitId;
	}
	public void setRetraitId(Integer retraitId) {
		this.retraitId = retraitId;
	}
	public EnvoiInscrit getEnvoiInscrit() {
		return envoiInscrit;
	}
	public void setEnvoiInscrit(EnvoiInscrit envoiInscrit) {
		this.envoiInscrit = envoiInscrit;
	}
	public EnvoiNonInscrit getEnvoiNonInscrit() {
		return envoiNonInscrit;
	}
	public void setEnvoiNonInscrit(EnvoiNonInscrit envoiNonInscrit) {
		this.envoiNonInscrit = envoiNonInscrit;
	}
	public Agentguichet getAgentguichet() {
		return agentguichet;
	}
	public void setAgentguichet(Agentguichet agentguichet) {
		this.agentguichet = agentguichet;
	}
	public Date getDateRetrait() {
		return dateRetrait;
	}
	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
}
