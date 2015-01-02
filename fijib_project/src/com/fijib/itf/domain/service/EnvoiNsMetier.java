package com.fijib.itf.domain.service;

import com.fijib.domain.dto.DtoClientNs;
import com.fijib.domain.dto.DtoEnvoiClient;
import com.fijib.domain.dto.DtoEnvoiNs;
import com.fijib.domain.dto.DtoInscriptionClient;

public interface EnvoiNsMetier {
	public void envoiNsVirement(DtoEnvoiNs infoEnvoiNs);
	public void envoiArgentBenf(DtoEnvoiClient infoEnvoi);
}
