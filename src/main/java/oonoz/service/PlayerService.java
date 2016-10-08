package oonoz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.PlayerManagerImpl;


/**
 * The Class PlayerService.
 * 
 * Description :
 * 		Manage the different business operations about Player entity.
 */
@Service
public class PlayerService {

	
	@Autowired
	private PlayerManagerImpl playerManager;

	/** The Constant REGEXPASSWORD. */
	protected final static String REGEXPASSWORD = "^(?=.*[0-9#\\$~<>\\|&-/])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$";
	
	/** The Constant REGEXMAIL. */
	protected final static String REGEXMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Sign-up a new player.
	 * 
	 * @param player
	 *            Contains player's information
	 * @throws WrongInformationException
	 *             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException
	 *             If the player which is signing-up already exist.
	 */
	public void signUp(Player player) throws WrongInformationException, PlayerAlreadyExistException {

		checkUsername(player.getUsername());
		checkPassword(player.getPassword());
		checkMail(player.getMail());
		checkLastName(player.getLastName());
		checkFirstName(player.getFirstName());
		checkBirthDate(player.getBirthDate());
		player.setIsActive(false);

		playerManager.create(player);
	}

	/**
	 * I check that the user pseudo is valid.
	 *
	 * @param pseudo
	 *            The pseudo of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	private void checkUsername(String pseudo) throws WrongInformationException {
		if (pseudo == null || pseudo.length() < 4 || pseudo.length() > 20) {
			throw new WrongInformationException("The pseudo is invalid !");
		}
	}

	/**
	 * I check that the user password is valid.
	 *
	 * @param password
	 *            The password of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	private void checkPassword(String password) throws WrongInformationException {
		if (password == null || !password.matches(PlayerService.REGEXPASSWORD)) {
			throw new WrongInformationException("The password is invalid !");
		}
	}

	/**
	 * I check that the user mail is valid.
	 *
	 * @param mail
	 *            The mail of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	private void checkMail(String mail) throws WrongInformationException {
		if (mail == null || !mail.matches(PlayerService.REGEXMAIL)) {
			throw new WrongInformationException("The mail is invalid !");
		}
	}

	/**
	 * I check that the user name is valid.
	 *
	 * @param name
	 *            The name of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	private void checkLastName(String name) throws WrongInformationException {
		if (name == null || name.length() < 3 || name.length() > 40) {
			throw new WrongInformationException("The lastname is invalid !");
		}
	}

	/**
	 * I check that the user first name is valid.
	 *
	 * @param firstName
	 *            The first name of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	private void checkFirstName(String firstName) throws WrongInformationException {
		if (firstName == null || firstName.length() < 3 || firstName.length() > 40) {
			throw new WrongInformationException("The firstname is invalid !");
		}
	}

	/**
	 * I check that the user birth date is valid.
	 *
	 * @param birthDate the birth date
	 * @throws WrongInformationException             raised if the information is not valid.
	 */
	private void checkBirthDate(Date birthDate) throws WrongInformationException {

		if (birthDate == null) {
			throw new WrongInformationException("The birthDate  is invalid !");
		}
	}
	
	/**
	 * I check that the user birth date is valid.
	 *
	 * @param isActive the is active
	 * @throws WrongInformationException             raised if the information is not valid.
	 */
	private void checkIsActive(boolean isActive) throws WrongInformationException {

		if (isActive) {
			throw new WrongInformationException("A new player can not be active on sign-up !");
		}
	}

	/**
	 * I check if the date format is correct.
	 *
	 * @param date the date
	 * @return 		true if the format is correct, false if not.
	 */
	private boolean isValidDate(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
