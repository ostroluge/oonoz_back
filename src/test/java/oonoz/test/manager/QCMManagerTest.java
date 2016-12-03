package oonoz.test.manager;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.OonozApplication;
import oonoz.domain.QCM;
import oonoz.manager.impl.QCMManagerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
//@TestPropertySource(locations = {"classpath:/test/config.properties"})
public class QCMManagerTest {
	
	
	@Autowired
	private QCMManagerImpl qcmManager;
	
	//TODO A retirer
	@Test
	public void getAllQCMsTest()  {
		List<QCM> qcms=qcmManager.getAllQCMs();
		assertNotNull(qcms);
	}
}
