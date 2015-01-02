package test_Dynamique_web_proj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.itf.domain.service.IRetraitMetier;

public class TestEnvoi {

	@Test
	public void test1() {

		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		IRetraitMetier iRetraitMetier = (IRetraitMetier) context.getBean("retrait");
		int[] res=iRetraitMetier.historiqueRetrait();
		System.out.println("dzdez "+res.length);
		for(int i: res){
			System.out.println(i);
		}
	}
}
