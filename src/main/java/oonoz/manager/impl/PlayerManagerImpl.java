package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.manager.PlayerManager;
import oonoz.repository.PlayerRepository;

/**
 * The Class PlayerManagerImpl.
 * 
 * Description :
 * 		Manage the different technical operations about Player entity.
 */
@Component(value = "playerManager")
public class PlayerManagerImpl implements PlayerManager {

	/** The player repository. */
	@Resource
	private PlayerRepository playerRepository;

	
	/**
	 * Check if the player does not already exist, then add the player to the database.
	 *
	 * @param player the player
	 * @throws PlayerAlreadyExistException if the player already exists.
	 */
	public void create(Player player) throws PlayerAlreadyExistException {

		List<Player> players = playerRepository.findByUsernameOrMail(player.getUsername(), player.getMail());
		if (players.isEmpty()) {
			player=playerRepository.save(player);	
			playerRepository.addSupplierRole(player.getId(),player.getUsername());
		} else
			throw new PlayerAlreadyExistException("The username or mail of " +player.getUsername()+ " already exist !");
	}
	
	
	/**
	 * Find a player by his mail.
	 *
	 * @param mail the mail
	 * @return the player
	 * @throws PlayerNotExistException if the player does not exist.
	 */
	public Player findByMail(String mail) throws PlayerNotExistException{
		
		Player player= playerRepository.findByMail(mail);
		if(player==null){
			throw new PlayerNotExistException("The player with the mail "+mail+" does not exist !");
		}
		
		return player;
	}
	
	/**
	 * Update.
	 *
	 * @param player the player
	 */
	public void update(Player player){		
		playerRepository.save(player);
	}

}
