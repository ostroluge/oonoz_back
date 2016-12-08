package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.domain.Authorities;
import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.manager.SupplierManager;
import oonoz.repository.AuthoritiesRepository;
import oonoz.repository.SupplierRepository;

/**
 * The Class SupplierManagerImpl.
 */
@Service(value = "supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	/** The supplier repository. */
	@Resource
	private SupplierRepository supplierRepository;

	/** The authorities repository. */
	@Resource
	private AuthoritiesRepository authoritiesRepository;

	/**
	 * Creates the.
	 *
	 * @param supplier the supplier
	 * @throws PlayerAlreadyExistException the player already exist exception
	 */
	public void create(Supplier supplier) throws PlayerAlreadyExistException {

		List<Supplier> suppliers = supplierRepository.findByUsernameOrMail(supplier.getUsername(), supplier.getMail());
		if (suppliers.isEmpty()) {

			supplier = supplierRepository.save(supplier);
			Authorities authorities = new Authorities();
			authorities.setIdAuthorities(supplier.getIdPlayer());
			authorities.setRole("ROLE_SUPPLIER");
			authorities.setUsername(supplier.getUsername());

			authoritiesRepository.save(authorities);
		} else {

			throw new PlayerAlreadyExistException(
					"The username or mail of " + supplier.getUsername() + " already exist !");
		}

	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the player
	 */
	public Player findById(long id){
		
		return supplierRepository.findOne(id);
	}
	
	/**
	 * Delete supplier by id.
	 *
	 * @param idPlayer the id player
	 */
	public void deleteSupplierById(long idPlayer){
		supplierRepository.deleteSupplierById(idPlayer);
	}
	
	/**
	 * Find not valid supplier.
	 *
	 * @return the list
	 */
	public List<Supplier> findNotValidSupplier(){
		List<Supplier> suppliers = supplierRepository.findByIsValidFalse();
		return suppliers;
	}
	
	
	/**
	 * Refuse supplier request.
	 *
	 * @param idPlayer the id player
	 */
	public void refuseSupplierRequest(long idPlayer){
		supplierRepository.deleteSupplierRequest(idPlayer);
	}
	
	/**
	 * Update.
	 *
	 * @param supplier the supplier
	 */
	public void update(Supplier supplier){		
		supplierRepository.save(supplier);
	}

	/**
	 * Accept supplier request.
	 *
	 * @param idPlayer the id player
	 */
	public void acceptSupplierRequest(Long idPlayer) {
		Supplier supplier = supplierRepository.findOne(idPlayer);
		supplier.setIsValid(true);
		supplierRepository.save(supplier);
	}

	/**
	 * Gets the suppliers.
	 *
	 * @return the suppliers
	 */
	public List<Supplier> getSuppliers() {
		return (List<Supplier>) supplierRepository.findAll();
	}
}
