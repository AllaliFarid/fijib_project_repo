package com.fijib.itf.domain.service;

import java.util.List;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoListprefere;
import com.fijib.impl.persistence.entity.Client;

public interface IlistePrefereMetier {
   public  List<DtoClient> getListPrefere(Client Client_env);
   public void addBenefToList(String email);
}
