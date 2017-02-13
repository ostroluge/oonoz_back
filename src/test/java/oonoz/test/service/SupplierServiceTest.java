package oonoz.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;

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
import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.SupplierManagerImpl;
import oonoz.repository.SupplierRepository;
import oonoz.service.SupplierService;
import oonoz.util.MailService;

/**
 * The Class SupplierServiceTest.
 * 
 * Description :
 * 		Test the methods of the SupplierService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
public class SupplierServiceTest {
	
	@Autowired
	@InjectMocks
	private SupplierService supplierService;

	@Mock
	private SupplierRepository supplierRepository;
	
	@Mock
	private MailService mailService;

	@Mock
	private SupplierManagerImpl supplierManager;
	/**
	 * Inits the a supplier object used by most of the test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
        MockitoAnnotations.initMocks(this);
	}
	
	
	/**
	 * Test a valid Signed-up supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 * @throws ParseException 
	 */
	@Test
	public void signUpSupplier() throws WrongInformationException, PlayerAlreadyExistException, MessagingException, ParseException{
		// [-- INITIALISATION --]
		Supplier supplier = new Supplier();
		supplier.setMail("vincentmargerin59@gmail.com");
		supplier.setUsername("auchan");
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setPassword("Password59");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);
		
		// [-- APPEL DU SERVICE --]
		supplierService.signUp(supplier);
		
		// [-- VERIFICATION --]
		final ArgumentCaptor<Supplier> argument = ArgumentCaptor.forClass(Supplier.class);
		Mockito.verify(this.supplierManager, Mockito.times(1)).create(argument.capture());
		Mockito.verify(this.mailService, Mockito.times(1)).sendValidationMail(argument.capture());
		
	}
	
	/**
	 * Test a valid Signed-up professional supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 * @throws ParseException 
	 */
	@Test
	public void signUpProfesionnalSupplier() throws PlayerAlreadyExistException, WrongInformationException, MessagingException, ParseException{
		// [-- INITIALISATION --]
		Supplier supplier = new Supplier();
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setPassword("Password59");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);
		supplier.setMail("jalzuritro@gmail.com");
		supplier.setUsername("jalzuritro");
		supplier.setIsPrivateIndividual(false);
		supplier.setSiretNumber("45569885255236");
		supplier.setCompanyAddress("Adresse de l'entreprise");
		supplier.setCompanyName("Nom entreprise");
		
		// [-- APPEL DU SERVICE --]
		supplierService.signUp(supplier);
		
		// [-- VERIFICATION --]
		
	}
	
	/**
	 * Test a valid Signed-up professional supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 * @throws ParseException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpProfesionnalSupplierWithBadAddress() throws PlayerAlreadyExistException, WrongInformationException, MessagingException, ParseException{
		// [-- INITIALISATION --]
		Supplier supplier = new Supplier();
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setPassword("Password59");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);
		supplier.setMail("jalzuritro@gmail.com");
		supplier.setUsername("jalzuritro");
		supplier.setIsPrivateIndividual(false);
		supplier.setSiretNumber("45569885255236");	
		supplier.setCompanyAddress("adress");
		supplier.setCompanyName("I");
		
		// [-- APPEL DU SERVICE --]
		supplierService.signUp(supplier);
		
		// [-- VERIFICATION --]
		//Must throw WrongInformationException
	}
	
	
	/**
	 * Test if the service throw a WrongInformationException when the password
	 * is not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException 
	 * @throws ParseException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpSupplierWithBadPassword() throws WrongInformationException, PlayerAlreadyExistException, MessagingException, ParseException {
		// [-- INITIALISATION --]
		Supplier supplier = new Supplier();
		supplier.setMail("vincentmargerin59@gmail.com");
		supplier.setUsername("auchan");
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);
		supplier.setPassword("badpwd");

		// [-- APPEL DU SERVICE --]
		supplierService.signUp(supplier);

		// [-- VERIFICATION --]
		//Must throw WrongInformationException
	}
	
		
	/**
	 * Test if the service throw a WrongInformationException when the mail is
	 * not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the supplier already exist exception
	 * @throws MessagingException 
	 * @throws ParseException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpSupplierWithBadMail() throws WrongInformationException, PlayerAlreadyExistException, MessagingException, ParseException {
		// [-- INITIALISATION --]
		Supplier supplier = new Supplier();
		supplier.setUsername("auchan");
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setPassword("Password59");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);	
		supplier.setMail("margerin.vincentpapamail.com");
	
		// [-- APPEL DU SERVICE --]
		supplierService.signUp(supplier);

		// [-- VERIFICATION --]
		//Must throw WrongInformationException
	}

}
