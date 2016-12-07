package oonoz.service;


import java.security.SecureRandom;

import javax.mail.MessagingException;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import oonoz.domain.Player;
import oonoz.dto.model.PlayerDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotActiveException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.PlayerManagerImpl;
import oonoz.util.CheckUserInformation;
import oonoz.util.FilteredSearch;
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

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

	/**
	 * Sign-up a new player.
	 *
	 * @param player Contains player's information
	 * @throws WrongInformationException  If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException  If the player which is signing-up already exist.
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
		player.setIsSupplier(false);
		String hashPassword=checkUserInformation.hashPassword(player.getPassword());
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
	 * Sign-up a new player already active without mail.
	 *
	 * @param player Contains player's information
	 * @throws WrongInformationException  If one of the information about the player is wrong.
	 * @throws PlayerAlreadyExistException  If the player which is signing-up already exist.
	 * @throws MessagingException the messaging exception
	 */
	public void signUpByAdmin(Player player) throws WrongInformationException, PlayerAlreadyExistException, MessagingException {

		checkUserInformation.checkUsername(player.getUsername());
		checkUserInformation.checkPassword(player.getPassword());
		checkUserInformation.checkMail(player.getMail());
		checkUserInformation.checkLastName(player.getLastName());
		checkUserInformation.checkFirstName(player.getFirstName());
		checkUserInformation.checkBirthDate(player.getBirthDate());
		player.setIsActive(true);
		player.setIsSupplier(false);
		String hashPassword=checkUserInformation.hashPassword(player.getPassword());
		if(hashPassword!=null){
			player.setPassword(hashPassword);
			playerManager.create(player);
		}
		else{
			throw new WrongInformationException("Password invalid");
		}
	}
	
	/* Validate mail of the new signed-up player.
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
	 * @throws PlayerNotExistException 
	 */
	//TODO use spring security authentication principal

	public void updatePlayer(Player player) throws WrongInformationException, PlayerNotExistException{
		
		checkUserInformation.checkUsername(player.getUsername());
		//checkUserInformation.checkPassword(player.getPassword());
		checkUserInformation.checkMail(player.getMail());
		checkUserInformation.checkLastName(player.getLastName());
		checkUserInformation.checkFirstName(player.getFirstName());
		checkUserInformation.checkBirthDate(player.getBirthDate());
		Player player_=playerManager.findByMail(player.getMail());
		player_.setUsername(player.getUsername());
		player_.setLastName(player.getLastName());
		player_.setFirstName(player.getFirstName());
		player_.setMail(player.getMail());
		player_.setBirthDate(player.getBirthDate());
		player_.setIsActive(player.getIsActive());
		player_.setIsSupplier(false);
		playerManager.update(player_);
	}
	
	public void deletePlayer(Long idPlayer) throws PlayerNotExistException{
		Player player_=playerManager.getPlayer(idPlayer);
		if(player_==null){
			throw new PlayerNotExistException("The player does not exist !");
		}
		playerManager.deletePlayer(player_.getIdPlayer());
		
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
		checkUserInformation.checkIsActive(player.getIsActive());
		String password=generateNewPassword();
		String hashPassword=checkUserInformation.hashPassword(password);
		player.setPassword(hashPassword);
		playerManager.update(player);
		player.setPassword(password);
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
			logger.error("Regenerate a new Password", e);
			return generateNewPassword();
		}

	}
	
	public Page<PlayerDto> filteredSearch(FilteredSearch filteredSearch) throws WrongInformationException{
				
		if(filteredSearch.getPageNumber()>=0 && filteredSearch.getPageSize()>=0){
			return playerManager.findsPageable(filteredSearch);
		}
		
		throw new WrongInformationException("The page number must be greater or equal than zero !");
		
	}
	
	public Player getPlayerById(long idPlayer){
		return playerManager.getPlayer(idPlayer);
	}
	
	public void changeStatusUser(long idPlayer) throws PlayerNotExistException{
		playerManager.changeStatusUser(idPlayer);
	}
	

}
