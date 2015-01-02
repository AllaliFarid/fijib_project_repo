package com.fijib.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Listeprefere;

public class DtoListprefere implements Serializable {
 
	 
	private List<Client>  listePrefere;
    
	public DtoListprefere() {
		super();
	}

	public DtoListprefere(List<Client> client_bene) {
		super();
		this.listePrefere = client_bene;
	}

	public List<Client> getListePrefere() {
		return listePrefere;
	}

	public void setListePrefere(List<Client> listePrefere) {
		this.listePrefere = listePrefere;
	}
}
