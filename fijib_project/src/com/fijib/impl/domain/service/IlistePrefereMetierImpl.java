package com.fijib.impl.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoListprefere;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Listeprefere;
import com.fijib.itf.domain.service.IlistePrefereMetier;
import com.fijib.itf.persistence.dao.IListePrefere;
import com.fijib.itf.persistence.dao.IUtilisateur;

@Service("ListePrefereMetier")
@Transactional
public class IlistePrefereMetierImpl implements IlistePrefereMetier {

	@Autowired
	private IListePrefere iListePrefereDao;
	
	@Autowired
	private IUtilisateur utilisateurDao;

	@Override
	public List<DtoClient> getListPrefere(Client client_env) {
		List<Listeprefere> list = iListePrefereDao.getListePreferer(client_env);
		List<DtoClient> clients = new ArrayList<>();
		Iterator<Listeprefere> i = list.iterator();

		Client client = new Client();
		while (i.hasNext()) {

			client = i.next().getClientByCinBene();

			DtoClient dtoClient = new DtoClient();
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
			clients.add(dtoClient);

		}
		return clients;
	}
	
	public void addBenefToList(String email){
		
	}

}
