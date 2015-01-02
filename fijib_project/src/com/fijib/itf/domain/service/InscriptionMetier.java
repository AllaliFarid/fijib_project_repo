package com.fijib.itf.domain.service;

import com.fijib.domain.dto.DtoInscriptionClient;

public interface InscriptionMetier {
	public void inscrire(DtoInscriptionClient infoClient, int flag);
	public boolean verifierClient(String cin, String login);
}
