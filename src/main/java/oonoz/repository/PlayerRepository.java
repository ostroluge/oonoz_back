package oonoz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Player;


// TODO: Auto-generated Javadoc
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
	
	
	/**
	 * Find by mail.
	 *
	 * @param mail the mail
	 * @return the player
	 */
	Player findByMail(String mail);
	
	/**
	 * Add a ROLE_SUPPLIER to the player entity.
	 *
	 * @param idUser the id user
	 * @param username the username
	 */
    @Transactional
    @Modifying
    @Query(value="insert into AUTHORITIES (id_player,username,role) values(?1,?2,'ROLE_SUPPLIER')",nativeQuery = true)
    void addSupplierRole(long idUser,String username);
}
