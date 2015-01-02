package com.fijib.web.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 









import com.fijib.domain.dto.DtoRetrait;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.Retrait;
import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.itf.domain.service.IRetraitMetier;

@ManagedBean
@Component
@SessionScoped
public class TableauBordMgdBean {

	@Autowired
	private IEnvoiMetier envoiMetier;
	@Autowired
	private IRetraitMetier iRetraitMetier;
	private String cin =new String(); 
	private List<EnvoiInscrit> listEnvois;
	private List<Retrait> listRetraits;
	private int x=1;
	private boolean etat=false;
 
	
	 
	public List<EnvoiInscrit> getListEnvois(){
		  listEnvois =envoiMetier.getListEnvoi(cin);
		  etat=true;
		  return listEnvois;
	}
	 
	public List<Retrait>  getListRetraits(){
		listRetraits = iRetraitMetier.getListREtrait(cin);
	   etat=true;
	   return listRetraits;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setListEnvois(List<EnvoiInscrit> listEnvois) {
		this.listEnvois = listEnvois;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
 
    public void affAccueil(){
    	x=1;
    }
    public void affEnvois(){
    	cin=null;
    	x=2;
    }
	public void affRetraits(){
		cin=null;
		x=3;
	}
	public void affHistEnvois(){
		x=4;
	}
    public void affHistRetraits(){
    	x=5;
    }
	public void affMessages(){
		x=6;
	}
	public String affCalFrais(){
		x=7;return "/securePages/AgentPages/accueilAgent";
	}
	public String affAgences(){
		x=8;
		return "/securePages/AgentPages/accueilAgent";
	}
}
