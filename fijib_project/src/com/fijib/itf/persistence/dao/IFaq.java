package com.fijib.itf.persistence.dao;

import java.util.List;

 import com.fijib.impl.persistence.entity.Faq;

public interface IFaq {
	 void create( Faq faq);
	    void edit( Faq faq);
	    void remove( Faq faq);
	    Faq find(Object id);
	    List< Faq> findAll();
	    int count();
}
