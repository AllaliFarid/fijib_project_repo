package com.fijib.impl.domain.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoInscriptionClient;
import com.fijib.impl.persistence.dao.ClientHome;
import com.fijib.impl.persistence.dao.UtilisateurHome;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Role;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.domain.service.InscriptionMetier;
import com.fijib.itf.persistence.dao.IClient;
import com.fijib.itf.persistence.dao.IRole;
import com.fijib.itf.persistence.dao.IUtilisateur;

@Service("inscriptionMetier")
@Transactional
public class InscriptionMetierImpl implements InscriptionMetier {

	@Autowired
	private IClient clientDao;
	@Autowired
	private IUtilisateur utilisateurDao;
	@Autowired
	private IRole roleDao;

	public void setClientDao(ClientHome clientDao) {
		this.clientDao = clientDao;
	}

	public void setUtilisateurDao(UtilisateurHome utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@Override
	public void inscrire(DtoInscriptionClient infoClient, int flag) {

		String pass = springEncoderMd5(infoClient.getPassword(), flag);
		Utilisateur user;
		
		if(flag != -1){
			user = new Utilisateur(infoClient.getCin(),
					infoClient.getEmail(), pass, "ACTIVE");
		}
		else{
			user = new Utilisateur(infoClient.getCin(),
					infoClient.getEmail(), pass, "NON_ACTIVE");
		}

		Client client = new Client(infoClient.getAdresseClient(),
				infoClient.getCodePostale(), infoClient.getDateNaissance(),
				infoClient.getFirstName(), infoClient.getLastName(),
				infoClient.getNumCompteBanc(), infoClient.getTel(),
				infoClient.getVille());

		Role rClient = new Role(user, "ROLE_CLIENT");

		client.setUtilisateur(user);
		user.setClient(client);

		utilisateurDao.create(user);
		clientDao.create(client);
		roleDao.create(rClient);
	}

	public boolean verifierClient(String cin, String email) {
		Utilisateur userCin = utilisateurDao.find(cin);
		Utilisateur userEmail = utilisateurDao.findUserByName(email);

		if (userCin == null && userEmail == null) {
			return false;
		} else {
			return true;
		}
	}

	public String springEncoderMd5(String mot, int flag) {

		if (flag != -1) {
			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			messageDigest.update(mot.getBytes(), 0, mot.length());
			String hashedPass = new BigInteger(1, messageDigest.digest())
					.toString(16);
			if (hashedPass.length() < 32) {
				hashedPass = "0" + hashedPass;
			}
			return hashedPass;
		}
		else 
			return null;
	}

	public InscriptionMetierImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
