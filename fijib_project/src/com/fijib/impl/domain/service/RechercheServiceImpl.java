package com.fijib.impl.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoClient;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Listeprefere;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.domain.service.RechercheService;
import com.fijib.itf.persistence.dao.IClient;
import com.fijib.itf.persistence.dao.IListePrefere;
import com.fijib.itf.persistence.dao.IUtilisateur;

@Service("rechercheService")
@Transactional
public class RechercheServiceImpl implements RechercheService {

	@Autowired
	private IUtilisateur utilisateurDao;
	@Autowired
	private IClient clientDao;
	@Autowired
	private IListePrefere listPrefereDao;

	@Override
	public List<DtoClient> getListRechercheEmail(String email) {
		Utilisateur user = utilisateurDao.findUserByName(email);

		List<DtoClient> clients = new ArrayList<DtoClient>();

		if (user != null) {
			Client client = clientDao.find(user.getCin());
			DtoClient dtoCl = new DtoClient(user.getCin(), client.getAdresseClient(),
					client.getCodePostale(), client.getDateNaissance(), client
							.getFirstName(), client.getLastName(), client
							.getNumCompteBanc(), client.getTel(), client
							.getVille());
			dtoCl.setUtilisateur(user);
			dtoCl.setEmail(user.getEmail());
			clients.add(dtoCl);
		}
		return clients;
	}

	@Override
	public List<DtoClient> getListRechercheNom(String nom) {

		List<DtoClient> clients = new ArrayList<DtoClient>();

		List<Client> clientList = clientDao.getClientParNom(nom);
		
		Utilisateur user;
		
		Iterator<Client> i = clientList.iterator();
		while(i.hasNext()){
		  
			Client cli = (Client) i.next();
		  
			user = utilisateurDao.find(cli.getCin());
			
			DtoClient dtoCl = new DtoClient(user.getCin(), cli.getAdresseClient(),
					cli.getCodePostale(), cli.getDateNaissance(), cli
							.getFirstName(), cli.getLastName(), 
							cli.getNumCompteBanc(), cli.getTel(), cli.getVille());
			dtoCl.setUtilisateur(user);
			dtoCl.setEmail(user.getEmail());
			clients.add(dtoCl);
		}

		return clients;
	}

	@Override
	public List<DtoClient> getListRechercheCin(String cin) {
		Utilisateur user = utilisateurDao.find(cin);

		List<DtoClient> clients = new ArrayList<DtoClient>();

		if (user != null) {
			Client client = clientDao.find(user.getCin());
			DtoClient dtoCl = new DtoClient(user.getCin(), client.getAdresseClient(),
					client.getCodePostale(), client.getDateNaissance(), client
							.getFirstName(), client.getLastName(), client
							.getNumCompteBanc(), client.getTel(), client
							.getVille());
			dtoCl.setUtilisateur(user);
			dtoCl.setEmail(user.getEmail());
			clients.add(dtoCl);
		}
		return clients;
	}
	
	public void saveBenefPrefere(DtoClient benef, Client client){
		
		Client benefCl = new Client(benef.getAdresseClient(),
				benef.getCodePostale(), benef.getDateNaissance(), 
				benef.getFirstName(), benef.getLastName(),
				benef.getNumCompteBanc(), benef.getTel(), 
				benef.getVille());
		benefCl.setCin(benef.getCin());
		
		Listeprefere newList = new Listeprefere(benefCl, client);
		
		listPrefereDao.create(newList);
	}
	
	public void supprimerPrefere(String cinBenef, Client client){
		List<Listeprefere> list = listPrefereDao.getListePreferer(client);
		
		Iterator<Listeprefere> i = list.iterator();
		while(i.hasNext()){
		  
			Listeprefere li = (Listeprefere) i.next();
			
			if(li.getClientByCinBene().getCin().equalsIgnoreCase(cinBenef)){
				listPrefereDao.remove(li);
			}
		}
	}

	public void setUtilisateurDao(IUtilisateur utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public void setClientDao(IClient clientDao) {
		this.clientDao = clientDao;
	}
	public RechercheServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
}
