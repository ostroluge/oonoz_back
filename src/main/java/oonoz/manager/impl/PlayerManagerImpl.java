package oonoz.manager.impl;

import static oonoz.repository.PlayerSpecifications.firstnameStartWith;
import static oonoz.repository.PlayerSpecifications.isActive;
import static oonoz.repository.PlayerSpecifications.isPlayer;
import static oonoz.repository.PlayerSpecifications.isSupplier;
import static oonoz.repository.PlayerSpecifications.isUnactive;
import static oonoz.repository.PlayerSpecifications.lastnameStartWith;
import static oonoz.repository.PlayerSpecifications.mailStartWith;
import static oonoz.repository.PlayerSpecifications.usernameStartWith;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import oonoz.domain.Authorities;
import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.dto.converter.PlayerDtoConverter;
import oonoz.dto.converter.SupplierDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.PlayerManager;
import oonoz.repository.AuthoritiesRepository;
import oonoz.repository.PlayerRepository;
import oonoz.util.FilteredSearch;


/**
 * The Class PlayerManagerImpl.
 * 
 * Description :
 * 		Manage the different technical operations about Player entity.
 */
@Component(value = "playerManager")
public class PlayerManagerImpl  implements PlayerManager {

	/** The player repository. */
	@Resource
	private PlayerRepository playerRepository;
	
	@Resource
	private AuthoritiesRepository authoritiesRepository;


		
	@Autowired
	private PlayerDtoConverter playerDtoConverter;
	
	@Autowired
	private SupplierDtoConverter supplierDtoConverter;

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
			Authorities authorities = new Authorities();
			authorities.setIdAuthorities(player.getIdPlayer());
			authorities.setRole("ROLE_PLAYER");
			authorities.setUsername(player.getUsername());
			
			authoritiesRepository.save(authorities);

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
	
	
	public Player findById(long id){
		return playerRepository.findOne(id);
	}
	
	/**
	 * Find all players. Results are split in several page.
	 * @param pageNumber
	 * @return
	 * 		A page of playerDto
	 * @throws WrongInformationException
	 */
	public Page<PlayerDto>findsPageable(FilteredSearch filteredSearch) {
		
		Specification<Player> spec=null;
		
		
		Pageable pageable=new PageRequest(filteredSearch.getPageNumber(),filteredSearch.getPageSize(),Direction.ASC,"idPlayer");
		
		
		if(filteredSearch.getUsernameSearch()!=null && !filteredSearch.getUsernameSearch().equals("")){			
			spec=where(usernameStartWith(filteredSearch.getUsernameSearch().toLowerCase()));
		}
		
		if(filteredSearch.getLastnameSearch()!=null && !filteredSearch.getLastnameSearch().equals("")){			
			spec=where(spec).and(lastnameStartWith(filteredSearch.getLastnameSearch().toLowerCase()));
		}
		
		if(filteredSearch.getFirstnameSearch()!=null && !filteredSearch.getFirstnameSearch().equals("")){			
			spec=where(spec).and(firstnameStartWith(filteredSearch.getFirstnameSearch().toLowerCase()));
		}
		
		if(filteredSearch.getMailSearch()!=null && !filteredSearch.getMailSearch().equals("")){			
			spec=where(spec).and(mailStartWith(filteredSearch.getMailSearch().toLowerCase()));
		}
		
		if(filteredSearch.isPlayerStatus()){			
			spec=where(spec).and(isPlayer());
		}
		
		if(filteredSearch.isSupplierStatus()){			
			spec=where(spec).and(isSupplier());
		}
		
		if(filteredSearch.isUserActive()){
			spec=where(spec).and(isActive());
		}
		
		if(filteredSearch.isUserInactive()){
			spec=where(spec).and(isUnactive());
		}
		
		
		
		Page<Player> playersPage =playerRepository.findAll(spec, pageable);
		
		
				
		PlayerDto playerDto=null;
    	List<PlayerDto> playersDto=new ArrayList<PlayerDto>();
		for(Player player: playersPage.getContent()){
			if(player instanceof Supplier){
				playerDto=supplierDtoConverter.convertToDto((Supplier)player);
			}
			else{
    		playerDto=playerDtoConverter.convertToDto(player);    		
			}
			playersDto.add(playerDto);
    	}
		
    	Page<PlayerDto> playersDtoPage= new PageImpl<>(playersDto,pageable,playersPage.getTotalElements());
    	
    	return playersDtoPage;
		 
	}
	
	public Player getPlayer(long id){
		return playerRepository.findOne(id);
	}
	
	public Player getPlayerByUsername(String username){
		return playerRepository.findByUsername(username);
	}
	
	public void deletePlayer(long id){
		 playerRepository.delete(id);
	}

	public void changeStatusUser(long idPlayer) throws PlayerNotExistException{
		Player player=playerRepository.findOne(idPlayer);
		if(player!=null){
			if(!player.getIsSupplier() && !(player instanceof Supplier)){
				playerRepository.updatePlayerToSupplier(idPlayer);
				playerRepository.createSupplierRow(idPlayer);
				player.setIsSupplier(true);
			}
			else if(player.getIsSupplier()){
				player.setIsSupplier(false);	
			}
			else{
				player.setIsSupplier(true);
			}
			playerRepository.save(player);
		}
		else{
			throw new PlayerNotExistException("The player does not exist !");
		}
	}
	
	
}
