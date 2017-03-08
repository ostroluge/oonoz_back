package oonoz.test.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.OonozApplication;
import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.manager.impl.PlayerManagerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OonozApplication.class)
@TestPropertySource(locations = { "classpath:/test/config.properties" })
public class PlayerManagerTest {

	/** The player manager. */
	@Autowired
	private PlayerManagerImpl playerManager;
	

	/**
	 * Test if the manager throw a PlayerAlreadyExistException when create a
	 * Player which has a existing username.
	 *
	 * @throws ParseException             the parse exception
	 * @throws PlayerAlreadyExistException             the player already exist exception
	 */
	@Test(expected = PlayerAlreadyExistException.class)
	public void createPlayerWithExistingUsername() throws ParseException, PlayerAlreadyExistException {
		// [-- INITIALISATION --]
		Player player = new Player();
		player.setMail("jilief@gmail.com");
		player.setUsername("Jilief");
		player.setFirstName("Julien");
		player.setLastName("Flamen");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("1990-08-15"));
		player.setPassword("Password59");
		player.setIsActive(false);
		player.setIsSupplier(false);

		// [-- APPEL DU SERVICE --]
		playerManager.create(player);

		// [-- VERIFICATION --]
		// Must throw PlayerAlreadyExistException
		
	}
	
	/**
	 * Test a player creation.
	 *
	 * @throws ParseException             the parse exception
	 * @throws PlayerAlreadyExistException             the player already exist exception
	 * @throws PlayerNotExistException                 the player not exist exception
	 */
	@Test
	public void createPlayer() throws PlayerAlreadyExistException, ParseException, PlayerNotExistException {
		// [-- INITIALISATION --]
		Player player = new Player();
		player.setMail("goodguy@gmail.com");
		player.setUsername("gguy");
		player.setFirstName("good");
		player.setLastName("guy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		player.setBirthDate(sdf.parse("2017-01-05"));
		player.setPassword("Password59");
		player.setIsActive(false);
		player.setIsSupplier(false);

		// [-- APPEL DU SERVICE --]
		playerManager.create(player);
		Player newPlayer=playerManager.findByMail("goodguy@gmail.com");
		// [-- VERIFICATION --]
		assertNotNull(newPlayer);		
	}
	
	@Test
	public void updatePlayer() throws PlayerNotExistException{
		// [-- INITIALISATION --]
		Player player= playerManager.getPlayerByUsername("Jilief");
		player.setLastName("Melenchon");
		// [-- APPEL DU SERVICE --]
		playerManager.update(player);
		
		// [-- VERIFICATION --]
		player= playerManager.getPlayerByUsername("Jilief");
		assertTrue(player.getLastName().equals("Melenchon"));
	}
	
	/**
	 * Test if the manager throw a PlayerNotExistException when find a
	 * Player with a not existing mail.
	 *
	 * @throws PlayerNotExistException
	 *             the player not exist exception
	 */
	@Test(expected = PlayerNotExistException.class)
	public void findPlayerWithMailNotExist() throws PlayerNotExistException {
		// [-- INITIALISATION --]
		// [-- APPEL DU SERVICE --]
		playerManager.findByMail("mailnotexist@mail.com");
		// [-- VERIFICATION --]
		// Must throw PlayerNotExistException	
	}
	
	/**
	 * Test the Player status changing
	 *
	 * @throws PlayerNotExistException
	 *             the player not exist exception
	 */
	@Test
	public void changeStatusPlayerToSupplier() throws PlayerNotExistException {
		// [-- INITIALISATION --]
		// [-- APPEL DU SERVICE --]
		playerManager.changeStatusUser(102);
		Player player= playerManager.findById(102);		
		// [-- VERIFICATION --]
		assertTrue(player.getIsSupplier());
		// Must throw PlayerNotExistException	
	}
	
	/**
	 * Test the Supplier status changing
	 *
	 * @throws PlayerNotExistException
	 *             the player not exist exception
	 */
	@Test
	public void changeStatusSupplierToPlayer() throws PlayerNotExistException {
		// [-- INITIALISATION --]
		// [-- APPEL DU SERVICE --]
		playerManager.changeStatusUser(104);
		Player player= playerManager.findById(104);		
		// [-- VERIFICATION --]
		assertFalse(player.getIsSupplier());
		assertTrue(player instanceof Supplier);
		// Must throw PlayerNotExistException			
	}
	

}
