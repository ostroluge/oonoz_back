package oonoz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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
	 * @param username
	 *            the username
	 * @param mail
	 *            the mail
	 * @return the list
	 */

	List<Supplier> findByUsernameOrMail(String username,String mail);
	
	/**
	 * Delete supplier by id.
	 *
	 * @param idPlayer the id player
	 * @return the int
	 */
	@Transactional
	@Modifying
	@Query(value="delete from supplier where id_supplier=?1 ",nativeQuery = true)
    int deleteSupplierById(long idPlayer);
	
	


	/**
	 * Find supplier's request.
	 *
	 * @return list of supplier
	 */
	List<Supplier> findByIsValidFalse();

	/**
	 * Delete supplier by siret.
	 *
	 * @param site the site
	 * @return the long
	 */
	@Transactional
	Long deleteBySiretNumber(String site);

	/**
	 * Delete supplier by username.
	 *
	 * @param username the username
	 * @return the long
	 */
	@Transactional
	Long deleteByUsername(String username);

	/**
	 * Delete supplier by idPlayer.
	 *
	 * @param idPlayer the id player
	 */
	@Transactional
	@Modifying
	@Query(value = "delete from supplier where id_player=?1", nativeQuery = true)
	void deleteSupplierRequest(long idPlayer);

}
