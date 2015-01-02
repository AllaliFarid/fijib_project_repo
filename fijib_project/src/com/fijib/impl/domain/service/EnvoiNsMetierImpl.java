package com.fijib.impl.domain.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoClientNs;
import com.fijib.domain.dto.DtoEnvoiClient;
import com.fijib.domain.dto.DtoEnvoiNs;
import com.fijib.domain.dto.DtoInscriptionClient;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;
import com.fijib.itf.domain.service.EnvoiNsMetier;
import com.fijib.itf.persistence.dao.IEnvoi;
import com.fijib.itf.persistence.dao.IEnvoiNonInscrit;

@Service("envoiNsMetier")
@Transactional
public class EnvoiNsMetierImpl implements EnvoiNsMetier {

	@Autowired
	private IEnvoiNonInscrit daoEnvNS;
	@Autowired
	private IEnvoi daoEnvoi;
	
	@Override
	public void envoiNsVirement(DtoEnvoiNs infoEnvoiNs) {

		Date date = new Date();

		EnvoiNonInscrit envoiNS = new EnvoiNonInscrit(
				infoEnvoiNs.getIdEnvoiNs(), infoEnvoiNs.getMontant(),
				infoEnvoiNs.getFrais(), infoEnvoiNs.getCinBenef(),
				infoEnvoiNs.getCinEnv(), date, infoEnvoiNs.isPaypal(),
				infoEnvoiNs.getCodeSourceEnvoi(), false);

		daoEnvNS.create(envoiNS);

	}

	public void envoiArgentBenf(DtoEnvoiClient infoEnvoi) {

		Date date = new Date();
		
		System.out.println(date.toString());

		EnvoiInscrit envoi = new EnvoiInscrit(infoEnvoi.getEnvoiId(), 
				infoEnvoi.getBeneficiare(), infoEnvoi.getEnvoyeur(), 
				null, infoEnvoi.getAunePersonne(), 
				date , infoEnvoi.getFrais(), 
				infoEnvoi.getMontant(), infoEnvoi.isPaypal(), 
				infoEnvoi.getCodeSourceEnvoi(), false, null);
		
		//daoEnvoi.create(envoi);
	}

	public EnvoiNsMetierImpl() {
		super();
	}

	public void setDtoEnvNS(IEnvoiNonInscrit daoEnvNS) {
		this.daoEnvNS = daoEnvNS;
	}
}