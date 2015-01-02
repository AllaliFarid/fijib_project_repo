package com.fijib.itf.domain.service;

import java.util.List;

import com.fijib.domain.dto.DtoClient;
import com.fijib.impl.persistence.entity.Client;

public interface IClientMetier {
	public DtoClient getClientDto(String cin);
	public Client getClient(String cin);
	public List<Client> getClientDebutCin(String cin);
 }
