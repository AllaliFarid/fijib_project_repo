package com.fijib.impl.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fijib.impl.persistence.entity.Administrateur;
import com.fijib.itf.domain.service.IAdminMetier;
import com.fijib.itf.persistence.dao.IAdmministrateur;

@Service("adminMetier")
public class AminImpl implements IAdminMetier{

	@Autowired
	private IAdmministrateur admministrateur;
	public Administrateur getAdmin(String cin){
		return admministrateur.findUserByName(cin);
	}
	
}
