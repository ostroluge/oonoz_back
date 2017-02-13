package oonoz.test.service;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.OonozApplication;
import oonoz.domain.QCM;
import oonoz.domain.Theme;
import oonoz.exception.QCMAlreadyExistException;
import oonoz.exception.QCMCreationException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.manager.impl.ThemeManagerImpl;
import oonoz.service.QCMService;

/**
 * The Class QCMServiceTest.
 * 
 * Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OonozApplication.class)
public class QCMServiceTest {

	/** The qcm service. */
	@Autowired
	@InjectMocks
	private QCMService qcmService;

	/** The qcm manager. */
	@Mock
	private QCMManagerImpl qcmManager;

	/** The theme manager. */
	@Mock
	private ThemeManagerImpl themeManager;

	/**
	 * Inits the.
	 *
	 * @throws ParseException
	 *             the parse exception
	 */
	@Before
	public void init() throws ParseException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createWithoutPrize() throws ThemeDoesNotExistException, QCMCreationException, WrongInformationException,
			QCMAlreadyExistException {
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
		qcm.setIdTheme(9999);

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		final ArgumentCaptor<QCM> argument = ArgumentCaptor.forClass(QCM.class);
		Mockito.verify(this.qcmManager, Mockito.times(1)).postQCM(argument.capture());
		
	}

	/**
	 * Creates the complete QCM.
	 *
	 * @throws ThemeDoesNotExistException
	 *             the theme does not exist exception
	 * @throws QCMCreationException
	 *             the QCM creation exception
	 * @throws WrongInformationException
	 *             the wrong information exception
	 * @throws QCMAlreadyExistException
	 *             the QCM already exist exception
	 */
	@Test
	public void createCompleteQCM() throws ThemeDoesNotExistException, QCMCreationException, WrongInformationException,
			QCMAlreadyExistException {
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
		qcm.setIdTheme(9999);
		qcm.setPrizeName("a prize");
		qcm.setPrizeDescription("a good description for a good prize");

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		final ArgumentCaptor<QCM> argument = ArgumentCaptor.forClass(QCM.class);
		Mockito.verify(this.qcmManager, Mockito.times(1)).postQCM(argument.capture());
		

	}

	/**
	 * Creates QCM without description.
	 *
	 * @throws ThemeDoesNotExistException
	 *             the theme does not exist exception
	 * @throws QCMCreationException
	 *             the QCM creation exception
	 * @throws WrongInformationException
	 *             the wrong information exception
	 * @throws QCMAlreadyExistException
	 *             the QCM already exist exception
	 */
	@Test(expected = WrongInformationException.class)
	public void createWithoutDescription() throws ThemeDoesNotExistException, QCMCreationException,
			WrongInformationException, QCMAlreadyExistException {

		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST2");
		// NO DESCRIPTION
		qcm.setCategory("sommatif");
		qcm.setFree(true);
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);
		qcm.setTheme(new Theme());

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithoutThemeId() throws WrongInformationException, QCMCreationException, QCMAlreadyExistException,
			ThemeDoesNotExistException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST2");
		qcm.setDescription("A good description for a good QCM");
		qcm.setCategory("sommatif");
		qcm.setFree(true);
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);
		// NO THEME ID

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithoutCategory() throws WrongInformationException, QCMCreationException,
			QCMAlreadyExistException, ThemeDoesNotExistException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST2");
		qcm.setDescription("A good description for a good QCM");
		// NO CATEGORY
		qcm.setFree(true);
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithBadPrizeName() throws WrongInformationException, QCMCreationException,
			QCMAlreadyExistException, ThemeDoesNotExistException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST2");
		qcm.setDescription("A good description for a good QCM");
		// NO CATEGORY
		qcm.setFree(true);
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);
		qcm.setPrizeName("");
		qcm.setPrizeDescription("a good description for a good prize");
		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithNullPrizeDescription() throws WrongInformationException, QCMCreationException,
			QCMAlreadyExistException, ThemeDoesNotExistException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST2");
		qcm.setDescription("A good description for a good QCM");
		// NO CATEGORY
		qcm.setFree(true);
		qcm.setMinimalScore(10);
		qcm.setIdSupplier(9999);
		qcm.setPrizeName("a good prize");
		qcm.setPrizeDescription(null);
		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithBadMinimalScore() throws ThemeDoesNotExistException, QCMCreationException,
			WrongInformationException, QCMAlreadyExistException {
		// [-- INITIALISATION --]
		QCM qcm = new QCM();
		qcm.setName("TEST1");
		qcm.setDescription("A good description for a good QCM");
		qcm.setCategory("sommatif");
		qcm.setFree(false);
		qcm.setPrice(10);
		qcm.setIcon("");
		qcm.setMinimalScore(-1);
		qcm.setIdSupplier(9999);
		qcm.setIdTheme(9999);

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}

	@Test(expected = WrongInformationException.class)
	public void createWithBadPrice() throws ThemeDoesNotExistException, QCMCreationException,
			WrongInformationException, QCMAlreadyExistException {
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
		qcm.setIdTheme(9999);
		qcm.setFree(false);
		qcm.setPrice(-1);

		// [-- APPEL DU SERVICE --]
		qcmService.postQCM(qcm);

		// [-- VERIFICATION --]
		// Must throw WrongInformationException
	}
}
