package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.exception.PlayerAlreadyExistException;
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
	 * @throws PlayerAlreadyExistException the player already exist exception
	 */
	public void create(Player player) throws PlayerAlreadyExistException {

		List<Player> players = playerRepository.findByUsernameOrMail(player.getUsername(), player.getPassword());
		if (players.isEmpty()) {
			playerRepository.save(player);			
		} else
			throw new PlayerAlreadyExistException("The username or mail of " +player.getUsername()+ " already exist !");
	}

}
