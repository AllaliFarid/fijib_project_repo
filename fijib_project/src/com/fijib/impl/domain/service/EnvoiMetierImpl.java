package com.fijib.impl.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.domain.dto.DtoEnvoi;
import com.fijib.impl.persistence.entity.EnvoiInscrit;
import com.fijib.impl.persistence.entity.EnvoiNonInscrit;
import com.fijib.itf.domain.service.CalculFrais;
import com.fijib.itf.domain.service.EnvoiCodeGenerateur;
import com.fijib.itf.domain.service.IClientMetier;
import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.itf.persistence.dao.IEnvoi;
import com.fijib.itf.persistence.dao.IEnvoiNonInscrit;
@Service 
@Transactional
public class EnvoiMetierImpl implements IEnvoiMetier {

    @Autowired
    private IClientMetier clientMetier;
	@Autowired
	private IEnvoi envoiDaoitf;
	@Autowired
	private IEnvoiNonInscrit IenvoiNonInscrit;
	@Autowired
	private CalculFrais calculFrais;
	@Autowired
	private EnvoiCodeGenerateur codeGenerateur;
	@Override
	public String envoyer(com.fijib.domain.dto.DtoEnvoi envoiDto) {
	    String codeTransfert=codeGenerateur.genererCodeEnvoi(envoiDto.getEnvoyeur().getCin(), 
				envoiDto.getEnvoyeur().getFirstName(), envoiDto.getEnvoyeur().getLastName());
		if(envoiDto.getBeneficiare() != null){
			EnvoiInscrit envoiDoa =new EnvoiInscrit();
			envoiDoa.setEnvoiId(codeTransfert);
			envoiDoa.setClientByCinBene(envoiDto.getBeneficiare());
			envoiDoa.setClientByCliCinEnv(envoiDto.getEnvoyeur());
			envoiDoa.setAgentguichet(envoiDto.getAgentguichet());
			envoiDoa.setDateEnv(new Date());
			envoiDoa.setFrais(calculFrais.calculerFrais(envoiDto.getMontant(), "VIREMENT"));
			envoiDoa.setMontant(envoiDto.getMontant());
			envoiDoa.setAunePersonne(envoiDto.getAunePersonne());
			envoiDoa.setEnvoiValide(true);
 				envoiDaoitf.create(envoiDoa); }
		else{
			EnvoiNonInscrit envoiNonInscrit=new EnvoiNonInscrit();
			 
			envoiNonInscrit.setAgentguichet(envoiDto.getAgentguichet());
			envoiNonInscrit.setCinBenef(envoiDto.getCin_beneficaire());;
			envoiNonInscrit.setAgentguichet(envoiDto.getAgentguichet());
			envoiNonInscrit.setCinEnv(envoiDto.getEnvoyeur().getCin());;
			envoiNonInscrit.setDateEnv(new Date());
			envoiNonInscrit.setFrais(calculFrais.calculerFrais(envoiDto.getMontant(), "VIREMENT"));;
			envoiNonInscrit.setMontant(envoiDto.getMontant());
			envoiNonInscrit.setIdEnvoiNs(codeTransfert);
			envoiNonInscrit.setEnvoiValide(true);
 			IenvoiNonInscrit.create(envoiNonInscrit);
		}
		return codeTransfert;
	}
	@Override
	public DtoEnvoi getEnvoi(String code) {
	 	EnvoiInscrit  envoiInscrit=envoiDaoitf.getEnvoiInscrit(code);
		EnvoiNonInscrit envoiNonInscrit=IenvoiNonInscrit.getEnvoiNonInscrit(code);
		DtoEnvoi dtoEnvoi=new DtoEnvoi();
	 	if(envoiInscrit !=null){
	 		dtoEnvoi.setCode(envoiInscrit.getEnvoiId());
			dtoEnvoi.setBeneficiare(envoiInscrit.getClientByCinBene());
			dtoEnvoi.setDateEnv(envoiInscrit.getDateEnv());
			dtoEnvoi.setEnvoyeur(envoiInscrit.getClientByCliCinEnv());
			dtoEnvoi.setFrais(envoiInscrit.getFrais());
			dtoEnvoi.setMontant(envoiInscrit.getMontant());
			dtoEnvoi.setInscrit(true);
 			return dtoEnvoi;
	 	
		}else if (envoiNonInscrit !=null) {
			dtoEnvoi.setCode(envoiNonInscrit.getIdEnvoiNs());
			dtoEnvoi.setCin_beneficaire(envoiNonInscrit.getCinBenef());
			dtoEnvoi.setBeneficiare(clientMetier.getClient(envoiNonInscrit.getCinBenef()));
			dtoEnvoi.setDateEnv(envoiNonInscrit.getDateEnv());
			dtoEnvoi.setEnvoyeur(clientMetier.getClient(envoiNonInscrit.getCinEnv()));
			dtoEnvoi.setFrais(envoiNonInscrit.getFrais());
			dtoEnvoi.setMontant(envoiNonInscrit.getMontant());
			dtoEnvoi.setInscrit(false);
			return dtoEnvoi;
	}
	return null;
	}
	@Override
	public EnvoiInscrit getEnvoiInsEntity(String code) {
 		return envoiDaoitf.find(code);
	}
	@Override
	public EnvoiNonInscrit getEnvoiNonInsEntity(String code) {
 		return IenvoiNonInscrit.find(code);
	}
	@Override
	public List<EnvoiInscrit> getListEnvoi(String cin) {
 		return envoiDaoitf.getListEnvoi(cin) ;
	}
	@Override
	public int[] historiqueEnvoi() {
		return envoiDaoitf.historiqueEnvoi();
	}
}