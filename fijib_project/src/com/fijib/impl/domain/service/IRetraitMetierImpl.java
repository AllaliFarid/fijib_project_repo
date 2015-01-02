package com.fijib.impl.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.fijib.impl.persistence.entity.Retrait;
import com.fijib.itf.domain.service.IRetraitMetier;
import com.fijib.itf.persistence.dao.IRetrait;
import com.liferay.portal.kernel.annotation.Transactional;

@Service("retrait")
@org.springframework.transaction.annotation.Transactional
public class IRetraitMetierImpl implements IRetraitMetier{
    
	@Autowired
	private IRetrait  iRetrait;
	
	@Override
	public void enregistrerRetrait(Retrait retrait) {
 		 iRetrait.create(retrait);
	}

	@Override
	public List<Retrait> getListREtrait(String cin) {
		return iRetrait.getListREtrait(cin);
	}

	@Override
	public int[] historiqueRetrait() {
 		return iRetrait.historiqueRetrait();
	}

 

}
