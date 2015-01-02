package com.fijib.impl.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoClient;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Role;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.domain.service.InscriptionAgentClientMetier;
import com.fijib.itf.persistence.dao.IClient;
import com.fijib.itf.persistence.dao.IRole;
import com.fijib.itf.persistence.dao.IUtilisateur;
@Service("inscription")
@Transactional
public class InscriptionAgentClientMetierImpl implements InscriptionAgentClientMetier {

	@Autowired
	private IClient  clientDao;
	@Autowired
	private IUtilisateur utilisateurDao;
	@Autowired
	private IRole roleDao;
	@Override
	public void inscrire(DtoClient infoClient) {
		Utilisateur user = new Utilisateur(infoClient.getCin(),null,
				null, "INACTIVE");
		
		Client client = new Client(infoClient.getAdresseClient(),
				infoClient.getCodePostale(),
				infoClient.getDateNaissance(),
				infoClient.getFirstName(),
				infoClient.getLastName(),
				infoClient.getNumCompteBanc(),
				infoClient.getTel(),
				infoClient.getVille());
		
		Role rClient = new Role(user, "ROLE_CLIENT");
		
		client.setUtilisateur(user);
		user.setClient(client);
		
		utilisateurDao.create(user);
		clientDao.create(client);
		roleDao.create(rClient);
	}
	
	public boolean verifierClient(String cin, String email){
		Utilisateur userCin = utilisateurDao.find(cin);
		Utilisateur userEmail = utilisateurDao.findUserByName(email);
		
		if(userCin==null && userEmail==null){
			return false;
		}
		else{
			return true;
		}
	}

 
	
 

}
