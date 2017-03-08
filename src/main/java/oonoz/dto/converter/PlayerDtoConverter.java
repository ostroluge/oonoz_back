package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.dto.model.PlayerDto;


/**
 * The Class PlayerDtoConverter.
 * 
 * Description :
 * 		Convert a PlayerDto to Player and vice versa.
 */
@Component
public class PlayerDtoConverter {
	
	
	/**
	 * Convert to entity.
	 *
	 * @param playerDto the player dto
	 * @return the player
	 */
	public Player convertToEntity(PlayerDto playerDto){
		
		Player player = new Player();
		player.setLastName(playerDto.getLastName());
		player.setFirstName(playerDto.getFirstName());
		player.setMail(playerDto.getMail());
		player.setUsername(playerDto.getUsername());
		player.setPassword(playerDto.getPassword());
		player.setBirthDate(playerDto.getBirthDate());
		player.setIsActive(playerDto.getIsActive());
		player.setIsSupplier(playerDto.getIsSupplier());
		
		return player;
	}

	/**
	 * Convert to dto.
	 *
	 * @param player the player
	 * @return the player dto
	 */
	public PlayerDto convertToDto(Player player){
		PlayerDto playerDto = new PlayerDto();
		playerDto.setIdPlayer(player.getIdPlayer());
		playerDto.setLastName(player.getLastName());
		playerDto.setFirstName(player.getFirstName());
		playerDto.setMail(player.getMail());
		playerDto.setUsername(player.getUsername());
		playerDto.setBirthDate(player.getBirthDate());
		playerDto.setIsActive(player.getIsActive());
		playerDto.setIsSupplier(player.getIsSupplier());
		return playerDto;
	}
}
