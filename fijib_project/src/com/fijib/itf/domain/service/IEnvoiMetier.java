package com.fijib.itf.domain.service;

import java.util.List;

import com.fijib.domain.dto.DtoEnvoi;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;
 

 
 
 
public interface IEnvoiMetier {

	
	public String envoyer(DtoEnvoi envoi);
    public DtoEnvoi getEnvoi(String code);
    public EnvoiInscrit getEnvoiInsEntity(String code);
    public EnvoiNonInscrit getEnvoiNonInsEntity(String code);
    public List<EnvoiInscrit> getListEnvoi(String cin);
    public int[] historiqueEnvoi();
    }
