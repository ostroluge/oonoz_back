package oonoz;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;


/**
 * The Class PlayerServiceTest.
 * 
 * Description :
 * 		Test the methods of the PlayerService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
public class PlayerServiceTest {

	
	@Autowired
	private PlayerService playerService;

	
	private Player player;

	/**
	 * Inits the a player object used by most of the test.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
		player = new Player();
		player.setMail("margerin.vincent@papamail.com");
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
	 * @throws ParseException the parse exception
	 */
	@Test(expected = PlayerAlreadyExistException.class)
	public void signUpPlayerExist() throws WrongInformationException, PlayerAlreadyExistException, ParseException {

		playerService.signUp(player);

	}

	/**
	 * Test if the service throw a WrongInformationException when the password
	 * is not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpPlayerWithBadPassword() throws WrongInformationException, PlayerAlreadyExistException {

		player.setPassword("badpwd");

		playerService.signUp(player);

	}

	/**
	 * Test if the service throw a WrongInformationException when the mail is
	 * not valid.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerAlreadyExistException the player already exist exception
	 */
	@Test(expected = WrongInformationException.class)
	public void signUpPlayerWithBadMail() throws WrongInformationException, PlayerAlreadyExistException {

		
		player.setMail("margerin.vincentpapamail.com");
		playerService.signUp(player);

	}
}
