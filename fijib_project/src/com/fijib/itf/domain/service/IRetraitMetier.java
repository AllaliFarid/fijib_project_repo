package com.fijib.itf.domain.service;

 
import java.util.List;

import com.fijib.impl.persistence.entity.Retrait;

public interface IRetraitMetier {
   public void enregistrerRetrait( Retrait retrait );
   public List<Retrait> getListREtrait(String cin);
   public int[] historiqueRetrait();
 
}
