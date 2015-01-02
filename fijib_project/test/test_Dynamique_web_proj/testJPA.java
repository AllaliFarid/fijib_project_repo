package test_Dynamique_web_proj;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fijib.itf.domain.service.InscriptionMetier;




public class testJPA {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
 

	@Test
	public void test1() {
		
		ClassPathXmlApplicationContext	context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //InscriptionMetier metier =(InscriptionMetier) context.getBean("inscriptionMetier");
	}

}
