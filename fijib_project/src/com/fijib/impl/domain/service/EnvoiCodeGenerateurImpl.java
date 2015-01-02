package com.fijib.impl.domain.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;
import com.fijib.itf.domain.service.EnvoiCodeGenerateur;
import com.fijib.itf.persistence.dao.IEnvoi;
import com.fijib.itf.persistence.dao.IEnvoiNonInscrit;

@Service("envoiCodeGenerateur")
@Transactional
public class EnvoiCodeGenerateurImpl implements EnvoiCodeGenerateur{
	
	@Autowired
	private IEnvoiNonInscrit daoEnvNs;
	@Autowired
	private IEnvoi daoEnvInscrit;
	
	
	public EnvoiCodeGenerateurImpl() {
		super();
	}

	@Override
	public String genererCodeEnvoi(String cin, String nom, String prenom) {
		
		List <EnvoiNonInscrit> envNs = daoEnvNs.findAll();
		List <EnvoiInscrit> envInscrit = daoEnvInscrit.findAll();
		
		String codeEnvoi = "VIDE";
		int flag = -1;
		
		while(codeEnvoi.equalsIgnoreCase("VIDE") && flag == -1){
			
			int sizeEnNS = envNs.size();
			int sizeEn = envInscrit.size();
			
			
			String lettreNom;
			String nombAl1;
			String lettreCin;
			String nombAl2;
			String lettrePrenom;
			String nombAl3;
			
			nombAl1 =""+ (10000+sizeEnNS+sizeEn) + (int)(Math.random() * (((10111+sizeEnNS) - (10000+sizeEnNS)) + 1));
			
			nombAl2 = ""+ (1000+sizeEn+sizeEnNS) + (int)(Math.random() * (((1111+sizeEn) - (1000+sizeEn)) + 1));
			
			nombAl3 = ""+ (100+sizeEnNS) + (int)(Math.random() * (((111+sizeEnNS) - (100+sizeEnNS)) + 1));
			
			lettreCin = cin.substring(0,1)+cin.substring(cin.length()-1,cin.length());
			lettreNom = nom.substring(0, 2);
			lettrePrenom = prenom.substring(0,2);
			
			codeEnvoi = lettreNom + nombAl1 + lettreCin + nombAl2 + lettrePrenom + nombAl3;
			
			Iterator i = envNs.iterator();
			while(i.hasNext()){
				EnvoiNonInscrit x = (EnvoiNonInscrit) i.next();
				if(!x.getIdEnvoiNs().equalsIgnoreCase(codeEnvoi)){
					flag = 0;
				}
			}
			
			i = envInscrit.iterator();
 			while(i.hasNext()){
				EnvoiInscrit y =  (EnvoiInscrit) i.next();
				if(!y.getEnvoiId().equalsIgnoreCase(codeEnvoi)){
					flag = 0;
				}
			}
			
		}
		
		return codeEnvoi;
	}
	
}
