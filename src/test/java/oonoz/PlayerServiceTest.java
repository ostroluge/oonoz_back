package oonoz;

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

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.PlayerManagerImpl;
import oonoz.service.PlayerService;
import oonoz.util.MailService;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerServiceTest.
 * 
 * Description :
 * 		Test the methods of the PlayerService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
public class PlayerServiceTest {


	/** The player service. */
	@Autowired
	@InjectMocks
	private PlayerService playerService;

	/** The mail service. */
	@Autowired
	@Mock
	private MailService mailService;

	@Mock
	private PlayerManagerImpl playerManager;
	
	/**
	 * Inits the a player object used by most of the test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test if the service throw a WrongInformationException when the password
	 * is not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException the messaging exception
	 * @throws ParseException the parse exception
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpPlayerWithBadPassword() throws WrongInformationException, PlayerAlreadyExistException, MessagingException, ParseException {
		//[-- INITIALISATION --]
		Player player = new Player();
		player.setMail("vincentmargerin59@gmail.com");
		player.setUsername("ElPadre");
		player.setFirstName("Vincent");
		player.setLastName("Margerin");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));
		player.setPassword("badpwd");

		//[-- APPEL DU SERVICE --]
		playerService.signUp(player);

		//[-- VERIFICATION --]
		//Must throw WrongInformationException
	}

	/**
	 * Test if the service throw a WrongInformationException when the mail is
	 * not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException the messaging exception
	 * @throws ParseException the parse exception
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpPlayerWithBadMail() throws WrongInformationException, PlayerAlreadyExistException, MessagingException, ParseException {
		//[-- INITIALISATION --]
		Player player = new Player();
		player.setUsername("ElPadre");
		player.setFirstName("Vincent");
		player.setLastName("Margerin");
		player.setPassword("Password59");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));
		player.setMail("margerin.vincentpapamail.com");

		//[-- APPEL DU SERVICE --]
		playerService.signUp(player);

		//[-- VERIFICATION --]
		//Must throw WrongInformationException
	}

	/**
	 * Sign up player test.
	 *
	 * @throws MessagingException the messaging exception
	 * @throws ParseException the parse exception
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 */
	@Test
	public void signUpPlayerTest() throws MessagingException, ParseException, WrongInformationException, PlayerAlreadyExistException  {
		//[-- INITIALISATION --]
		Player player = new Player();
		player.setMail("vincentmargerin59@gmail.com");
		player.setUsername("ElPadre");
		player.setFirstName("Vincent");
		player.setLastName("Margerin");
		player.setPassword("Password59");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));

		//[-- APPEL DU SERVICE --]
		playerService.signUp(player);

		//[-- VERIFICATION --]
		final ArgumentCaptor<Supplier> argument = ArgumentCaptor.forClass(Supplier.class);
		Mockito.verify(this.playerManager, Mockito.times(1)).create(argument.capture());
		Mockito.verify(this.mailService, Mockito.times(1)).sendValidationMail(argument.capture());
	}

	/**
	 * Test if the generationPassword function throw an exception with no active user.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerNotExistException the player not exist exception
	 * @throws MessagingException the messaging exception
	 * @throws PlayerNotActiveException the player not active exception
	 * @throws ParseException the parse exception
	 */
	@Test(expected = PlayerNotActiveException.class)
	public void generateNewPasswordWithNoActiveUser() throws WrongInformationException, PlayerNotExistException, MessagingException, PlayerNotActiveException, ParseException{
		//[-- INITIALISATION --]
		Player player = new Player();
		player.setMail("vincentmargerin59@gmail.com");
		player.setUsername("ElPadre");
		player.setFirstName("Vincent");
		player.setLastName("Margerin");
		player.setPassword("Password59");
		player.setIsActive(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));

		Mockito.when(playerManager.findByMail(Mockito.anyString())).thenReturn(player);

		//[-- APPEL DU SERVICE --]
		playerService.generatePassword("vincentmargerin59@gmail.com");

		//[-- VERIFICATION --]
		//Throw PlayerNotActiveException
	}
}
