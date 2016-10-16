package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Player;
import oonoz.domain.Supplier;

/**
 * The Interface SupplierRepository.
 * 
 * Description : Manage database access about Supplier entity
 */
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
	
	/**
	 * Find by username or mail.
	 *
	 * @param username the username
	 * @param mail the mail
	 * @return the list
	 */
	List<Player> findByUsernameOrMail(String username,String mail);

}
