package com.fijib.impl.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoClient;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.itf.domain.service.IClientMetier;
import com.fijib.itf.persistence.dao.IClient;

@Service("iclientMetier")
@Transactional
public class IClientMetierImpl  implements IClientMetier{

	@Autowired
	private IClient  clientDao;
	@Override
	public DtoClient getClientDto (String cin){
		
		Client client= clientDao.find(cin);
		if(client !=null){
		 DtoClient dtoClient=new DtoClient();
		 dtoClient.setCin(client.getCin());		 
		 dtoClient.setAdresseClient(client.getAdresseClient());
		 dtoClient.setCodePostale(client.getCodePostale());
		 dtoClient.setDateNaissance(client.getDateNaissance());
		 dtoClient.setFirstName(client.getFirstName());
		 dtoClient.setLastName(client.getLastName());
		 dtoClient.setNumCompteBanc(client.getNumCompteBanc());
		 dtoClient.setTel(client.getTel());
		 dtoClient.setVille(client.getVille());
		 dtoClient.setEmail(client.getUtilisateur().getEmail());
		 return dtoClient;}
		else return null;
	}
	@Override
	public Client getClient(String cin) {
		Client client=clientDao.find(cin);
		if(client != null)
 		return client;
		else return null;
	}
	@Override
	public List<Client> getClientDebutCin(String cin) {
		return clientDao.getClientDebutCin(cin);
	}

 
}
