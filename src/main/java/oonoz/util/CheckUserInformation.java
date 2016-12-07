package oonoz.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.WrongInformationException;


/**
 * The Class CheckUserInformation.
 * 
 * Description :
 */
@Component
public class CheckUserInformation {
	
	/** The Constant REGEXPASSWD. */
	public final static String REGEXMDP = "^(?=.*[0-9#\\$~<>\\|&-/])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$";
	
	/** The Constant REGEXMAIL. */
	protected final static String REGEXMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CheckUserInformation.class);
	/**
	 * I check that the user pseudo is valid.
	 *
	 * @param pseudo
	 *            The pseudo of the user.
	 * @throws WrongInformationException
	 *             raised if the information is not valid.
	 */
	public void checkUsername(String pseudo) throws WrongInformationException {
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
	public void checkPassword(String password) throws WrongInformationException {
		if (password == null || !password.matches(CheckUserInformation.REGEXMDP)) {
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
	public void checkMail(String mail) throws WrongInformationException {
		if (mail == null || !mail.matches(CheckUserInformation.REGEXMAIL)) {
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
	public void checkLastName(String name) throws WrongInformationException {
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
	public void checkFirstName(String firstName) throws WrongInformationException {
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
	public void checkBirthDate(Date birthDate) throws WrongInformationException {

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
	public void checkIsNotActive(boolean isActive) throws WrongInformationException {

		if (isActive) {
			throw new WrongInformationException("A new player can not be active on sign-up !");
		}
	}
	
	/**
	 * I check that the user birth date is valid.
	 *
	 * @param isActive the is active
	 * @throws PlayerNotActiveException the player not active exception
	 */
	public void checkIsActive(boolean isActive) throws PlayerNotActiveException {

		if (!isActive) {
			throw new PlayerNotActiveException("The player is not active !");
		}
	}
	
	
	/**
	 * Check supplier is valid.
	 *
	 * @param isActive the is active
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkSupplierIsValid(boolean isActive) throws WrongInformationException {

		if (isActive) {
			throw new WrongInformationException("A new supplier can not be valid state on sign-up !");
		}
	}
	
	/**
	 * Check company name.
	 *
	 * @param companyName the company name
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkCompanyName(String companyName) throws WrongInformationException{
		if (companyName == null || companyName.length() < 3 || companyName.length() > 40) {
			throw new WrongInformationException("The company name is invalid !");
		}
	}
	
	/**
	 * Check company address.
	 *
	 * @param companyAddress the company address
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkCompanyAddress(String companyAddress) throws WrongInformationException{
		if (companyAddress == null || companyAddress.length() < 3 || companyAddress.length() > 40) {
			throw new WrongInformationException("The company address is invalid !");
		}
	}
	
	/**
	 * Check siret number.
	 *
	 * @param siretNumber the siret number
	 * @throws WrongInformationException the wrong information exception
	 */
	public void checkSiretNumber(String siretNumber) throws WrongInformationException{
		if (siretNumber == null || siretNumber.length() < 14 || siretNumber.length() > 14) {
			throw new WrongInformationException("The siret number is invalid !");
		}
	}

	/**
	 * I check if the date format is correct.
	 *
	 * @param date the date
	 * @return 		true if the format is correct, false if not.
	 */
	public boolean isValidDate(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	/**
	 * Hash the user password with sha-256 algorithm.
	 * @param password
	 * 		the user password 
	 * @return
	 * 		the hash user password
	 */
	public String hashPassword(String password){
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash=messageDigest.digest(password.getBytes("UTF-8"));			
			String hashPassword=DatatypeConverter.printHexBinary(hash).toLowerCase();
			return hashPassword;
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("Error during encoding password", e);
		}
		
		return null;
	}
}
