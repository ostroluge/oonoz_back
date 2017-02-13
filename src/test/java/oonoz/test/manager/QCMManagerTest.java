package oonoz.test.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.OonozApplication;
import oonoz.domain.QCM;
import oonoz.exception.QCMCreationException;
import oonoz.manager.impl.QCMManagerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OonozApplication.class)
@TestPropertySource(locations = { "classpath:/test/config.properties" })
public class QCMManagerTest {

	/** The player manager. */
	@Autowired
	private QCMManagerImpl qcmManager;

	@Test(expected = QCMCreationException.class)
	public void createWithNonExistingTheme() throws QCMCreationException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST1");
		qcm.setDescription("A good description for a good QCM");
		qcm.setCategory("sommatif");
		qcm.setFree(false);
		qcm.setPrice(10);
		qcm.setIcon("");
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(101);
		qcm.setIdTheme(9999);

		// [-- APPEL DU SERVICE --]
		qcmManager.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test
	public void createWithNonExistingSubTheme() {

	}

	@Test(expected = QCMCreationException.class)
	public void createWithNonExistingSupplier() throws QCMCreationException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST1");
		qcm.setDescription("A good description for a good QCM");
		qcm.setCategory("sommatif");
		qcm.setFree(false);
		qcm.setPrice(10);
		qcm.setIcon("");
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);
		qcm.setIdTheme(101);

		// [-- APPEL DU SERVICE --]
		qcmManager.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test
	public void createWithExistingName() {

	}

	@Test
	public void createWithSubThemeNotOwnByTheme() {

	}

}
