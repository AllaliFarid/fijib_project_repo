package com.fijib.impl.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.persistence.dao.IUtilisateur;
@Service("utilisateurMetier")
@Transactional
public class UtilisateurMetierImp implements IUtilisateurMetier {

	@Autowired
	private IUtilisateur iUtilisateur;
	@Override
	public void addUser(Utilisateur utilisateur) {
     		iUtilisateur.create(utilisateur);
	}
	@Override
	public List<Utilisateur> getAll() {
        
		return iUtilisateur.findAll();
	}
	@Override
	public Utilisateur findUserByName(String userName) {
		return iUtilisateur.findUserByName(userName);
	}
	
	public Utilisateur findUser(String cin){
		return iUtilisateur.find(cin);
	}
}
