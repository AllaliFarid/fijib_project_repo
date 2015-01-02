package test_Dynamique_web_proj;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fijib.itf.domain.service.IAdminMetier;

public class TestGetUser {

	
	
	@Test
	public void test1() {
		
		ClassPathXmlApplicationContext	context=new ClassPathXmlApplicationContext("applicationContext.xml");
        IAdminMetier adminMetier=(IAdminMetier) context.getBean("adminMetier");
        System.out.println(adminMetier.getAdmin("DF4564").getCin());
	}

}
