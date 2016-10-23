package oonoz.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.PlayerManagerImpl;
import oonoz.util.CheckUserInformation;
import oonoz.util.MailService;

/**
 * The Class PlayerService.
 * 
 * Description :
 * 		Manage the different business operations about Player entity.
 */
@Service
public class PlayerService {

	
	/** The player manager. */
	@Autowired
	private PlayerManagerImpl playerManager;
	
		
	/** The mail service. */
	@Autowired
	private MailService mailService;

	/** The check user information. */
	@Autowired
	private CheckUserInformation checkUserInformation;

	/**
	 * Sign-up a new player.
	 *
	 * @param player            Contains player's information
	 * @throws WrongInformationException             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException             If the player which is signing-up already exist.
	 * @throws MessagingException the messaging exception
	 */
	public void signUp(Player player) throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		checkUserInformation.checkUsername(player.getUsername());
		checkUserInformation.checkPassword(player.getPassword());
		checkUserInformation.checkMail(player.getMail());
		checkUserInformation.checkLastName(player.getLastName());
		checkUserInformation.checkFirstName(player.getFirstName());
		checkUserInformation.checkBirthDate(player.getBirthDate());
		player.setIsActive(false);

		String hashPassword=hashPassword(player.getPassword());
		if(hashPassword!=null){
			player.setPassword(hashPassword);
			playerManager.create(player);
			mailService.sendValidationMail(player);
		}
		else{
			throw new WrongInformationException("Password invalid");
		}
	}
	
	/**
	 * Hash the user password with sha-256 algorithm.
	 * @param password
	 * 		the user password 
	 * @return
	 * 		the hash user password
	 */
	private String hashPassword(String password){
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash=messageDigest.digest(password.getBytes("UTF-8"));			
			String hashPassword=DatatypeConverter.printHexBinary(hash).toLowerCase();
			return hashPassword;
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * Validate mail of the new signed-up player.
	 *
	 * @param mail the mail
	 * @param hash the hash
	 * @throws PlayerNotExistException the player not exist exception
	 * @throws WrongInformationException the wrong information exception
	 */
	public void validationMail(String mail,String hash) throws PlayerNotExistException, WrongInformationException{
		
		 if (mail.hashCode() == Integer.valueOf(hash)) {
            Player player=playerManager.findByMail(mail);
            player.setIsActive(true);
            playerManager.update(player);
         }
		 else{
			 throw new WrongInformationException("The key and the mail does not correspond !");
		 }
			 
	}
	
	/**
	 * Update player.
	 *
	 * @param player the player
	 * @throws WrongInformationException the wrong information exception
	 */
	//TODO use spring security authentication principal
	public void updatePlayer(Player player) throws WrongInformationException{
		
		checkUserInformation.checkUsername(player.getUsername());
		checkUserInformation.checkPassword(player.getPassword());
		checkUserInformation.checkMail(player.getMail());
		checkUserInformation.checkLastName(player.getLastName());
		checkUserInformation.checkFirstName(player.getFirstName());
		checkUserInformation.checkBirthDate(player.getBirthDate());
		
		playerManager.update(player);
	}
	
	
	/**
	 * Generate password.
	 *
	 * @param mail the mail
	 * @throws WrongInformationException the wrong information exception
	 * @throws PlayerNotExistException the player not exist exception
	 * @throws MessagingException the messaging exception
	 * @throws PlayerNotActiveException the player not active exception
	 */
	public void generatePassword(String mail) throws WrongInformationException, PlayerNotExistException, MessagingException, PlayerNotActiveException{
		
		checkUserInformation.checkMail(mail);
		Player player=playerManager.findByMail(mail);
		checkUserInformation.checkIsActive(player.isActive());
		String password=generateNewPassword();
		player.setPassword(password);
		playerManager.update(player);
		mailService.sendNewGeneratePasswordMail(player);	
	}

	/**
	 * Generation a new password with CheckUserInformation.REGEXPASSWORD constraints
	 * @return
	 * 		the new generated password.
	 */
	private String generateNewPassword(){
		
		String password = RandomStringUtils.random(9, 0, 0, true, true, null, new SecureRandom());
		
		try{
		checkUserInformation.checkPassword(password);
		return password;
		}
		catch(WrongInformationException e){
			return generateNewPassword();
		}
		
	}

}
