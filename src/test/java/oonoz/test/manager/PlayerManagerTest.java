package oonoz.test.manager;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

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
import oonoz.manager.impl.SupplierManagerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OonozApplication.class)
@TestPropertySource(locations = { "classpath:/test/config.properties" })
public class PlayerManagerTest {

	/** The player manager. */
	@Autowired
	private PlayerManagerImpl playerManager;
	
	/** The player manager. */
	@Autowired
	private SupplierManagerImpl supplierManager;

	/**
	 * Test if the manager throw a PlayerAlreadyExistException when create a
	 * Player which has a existing username.
	 *
	 * @throws PlayerAlreadyExistException
	 *             the player already exist exception
	 * @throws ParseException
	 *             the parse exception
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
