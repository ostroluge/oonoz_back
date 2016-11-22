package oonoz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface PlayerRepository extends CrudRepository<Player, Long>,JpaSpecificationExecutor<Player>{


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
    @Query(value="insert into AUTHORITIES (id_player,username,role) values(?1,?2,'ROLE_PLAYER')",nativeQuery = true)
    void addSupplierRole(long idUser,String username);
    
    
    /**
     * Find
     * @param pageable
     * @return
     */
    Page<Player> findByIsSupplierFalse(Pageable pageable);
    
    
   
    //Page<Player> findAll(Pageable pageable);
    
    
}
