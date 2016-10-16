package oonoz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
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
	
	@Autowired
	private MailService mailService;

	@Autowired
	private CheckUserInformation checkUserInformation;

	/**
	 * Sign-up a new player.
	 * 
	 * @param player
	 *            Contains player's information
	 * @throws WrongInformationException
	 *             If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException
	 *             If the player which is signing-up already exist.
	 * @throws MessagingException 
	 */
	public void signUp(Player player) throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		checkUserInformation.checkUsername(player.getUsername());
		checkUserInformation.checkPassword(player.getPassword());
		checkUserInformation.checkMail(player.getMail());
		checkUserInformation.checkLastName(player.getLastName());
		checkUserInformation.checkFirstName(player.getFirstName());
		checkUserInformation.checkBirthDate(player.getBirthDate());
		player.setIsActive(false);

		playerManager.create(player);
		mailService.sendValidationMail(player);
		
	}
	
	
	/**
	 * Validate mail of the new signed-up player.
	 *
	 * @param mail the mail
	 * @param hash the hash
	 * @throws PlayerNotExistException the player not exist exception
	 * @throws WrongInformationException 
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

	

}
