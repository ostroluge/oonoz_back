package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Player;


/**
 * The Interface PlayerRepository.
 * 
 * Description :
 * 		Manage database access about Player entity
 */
public interface PlayerRepository extends CrudRepository<Player, Long>{


	/**
	 * Find by username or mail.
	 *
	 * @param username the username
	 * @param mail the mail
	 * @return the list
	 */
	List<Player> findByUsernameOrMail(String username,String mail);
}
