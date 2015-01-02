package com.fijib.itf.domain.service;

import java.util.List;

import com.fijib.domain.dto.DtoClient;
import com.fijib.impl.persistence.entity.Client;

public interface RechercheService {
	public List<DtoClient> getListRechercheEmail(String email);
	public List<DtoClient> getListRechercheNom(String nom);
	public List<DtoClient> getListRechercheCin(String cin);
	public void saveBenefPrefere(DtoClient benef, Client client);
	public void supprimerPrefere(String cinBenef, Client client);
}
