package oonoz;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.repository.SupplierRepository;
import oonoz.service.SupplierService;
import oonoz.util.MailService;

/**
 * The Class PlayerServiceTest.
 * 
 * Description :
 * 		Test the methods of the PlayerService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
public class SupplierServiceTest {
	
	
	
	private Supplier supplier;
	
	
	@Autowired
	private SupplierService supplierService;

	@Mock
	private SupplierRepository supplierRepository;
	
	@Mock
	private MailService mailService;

	/**
	 * Inits the a supplier object used by most of the test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
		supplier = new Supplier();
		supplier.setMail("vincentmargerin59@gmail.com");
		supplier.setUsername("auchan");
		supplier.setFirstName("Vincent");
		supplier.setLastName("Margerin");
		supplier.setPassword("Password59");
		supplier.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		supplier.setBirthDate(sdf.parse("1990-08-15"));
		supplier.setIsValid(false);
	}
	
	
	/**
	 * Test a valid Signed-up supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 */
	@Test
	public void signUpSupplier() throws PlayerAlreadyExistException, WrongInformationException, MessagingException{
		supplierService.signUp(supplier);
		
	}
	
	/**
	 * Test a valid Signed-up professional supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 */
	@Test
	public void signUpProfesionnalSupplier() throws PlayerAlreadyExistException, WrongInformationException, MessagingException{
		supplier.setMail("jalzuritro@gmail.com");
		supplier.setUsername("jalzuritro");
		supplier.setIsPrivateIndividual(false);
		supplier.setSiretNumber("45569885255236");	
		supplierService.signUp(supplier);
		
	}
	
	/**
	 * Test a valid Signed-up professional supplier.
	 *
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws MessagingException the messaging exception
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpProfesionnalSupplierWithBadAddress() throws PlayerAlreadyExistException, WrongInformationException, MessagingException{
		supplier.setMail("jalzuritro@gmail.com");
		supplier.setUsername("jalzuritro");
		supplier.setIsPrivateIndividual(false);
		supplier.setSiretNumber("45569885255236");	
		supplier.setCompanyAddress("adress");
		supplier.setCompanyName("I");
		supplierService.signUp(supplier);		
	}
	
	
	/**
	 * Test if the service throw a WrongInformationException when the password
	 * is not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpSupplierWithBadPassword() throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		supplier.setPassword("badpwd");

		supplierService.signUp(supplier);

	}
	
		
	/**
	 * Test if the service throw a WrongInformationException when the mail is
	 * not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the supplier already exist exception
	 * @throws MessagingException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpSupplierWithBadMail() throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		
		supplier.setMail("margerin.vincentpapamail.com");
		supplierService.signUp(supplier);

	}

}
