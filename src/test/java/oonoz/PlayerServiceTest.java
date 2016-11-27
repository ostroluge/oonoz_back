package oonoz;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
import oonoz.util.MailService;


/**
 * The Class PlayerServiceTest.
 * 
 * Description :
 * 		Test the methods of the PlayerService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class PlayerServiceTest {

	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MailService mailService;

	
	private Player player;

	/**
	 * Inits the a player object used by most of the test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
		player = new Player();
		player.setMail("vincentmargerin59@gmail.com");
		player.setUsername("ElPadre");
		player.setFirstName("Vincent");
		player.setLastName("Margerin");
		player.setPassword("Password59");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));
	}

	/**
	 * Test if the service throw a PlayerAlreadyExistException when a exist
	 * player sign-up.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException 
	 * @throws ParseException the parse exception
	 */
	@Test(expected = PlayerAlreadyExistException.class)
	public void signUpPlayerExist() throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		playerService.signUp(player);

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
	public void signUpPlayerWithBadPassword() throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		player.setPassword("badpwd");

		playerService.signUp(player);

	}

	/**
	 * Test if the service throw a WrongInformationException when the mail is
	 * not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 * @throws MessagingException 
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpPlayerWithBadMail() throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		
		player.setMail("margerin.vincentpapamail.com");
		playerService.signUp(player);

	}
	
	/**
	 * Test the send validation mail service.
	 * @throws MessagingException 
	 *
	 * @throws MessagingException if a error occurs with smtp server.
	 */
	@Test
	public void sendValidationMail() throws MessagingException  {

		mailService.sendValidationMail(player);

	}
	
	/**
	 * Test if the generationPassword function throw an exception with no active user.
	 * 
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerNotExistException the player not exist exception
	 * @throws MessagingException the messaging exception
	 * @throws PlayerNotActiveException the player not active exception
	 */
	@Test(expected = PlayerNotActiveException.class)
	public void generateNewPasswordWithNoActiveUser() throws WrongInformationException, PlayerNotExistException, MessagingException, PlayerNotActiveException{
		playerService.generatePassword("brad.pitt@mail.com");
	}
}
