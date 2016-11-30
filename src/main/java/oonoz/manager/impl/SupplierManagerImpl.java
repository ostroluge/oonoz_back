package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import oonoz.domain.Authorities;
import oonoz.domain.Player;
import oonoz.domain.Supplier;
import oonoz.exception.PlayerAlreadyExistException;
import oonoz.manager.SupplierManager;
import oonoz.repository.AuthoritiesRepository;
import oonoz.repository.SupplierRepository;

@Service(value = "supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	@Resource
	private SupplierRepository supplierRepository;

	@Resource
	private AuthoritiesRepository authoritiesRepository;

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
	

	public Player findById(long id){
		
		return supplierRepository.findOne(id);
	}
	
	
	
	public void deleteSupplierById(long idPlayer){
		supplierRepository.deleteSupplierById(idPlayer);
	}
	
	

	public List<Supplier> findNotValidSupplier(){
		List<Supplier> suppliers = supplierRepository.findByIsValidFalse();
		return suppliers;
	}
	
	
	public void refuseSupplierRequest(long idPlayer){
		supplierRepository.deleteSupplierRequest(idPlayer);
	}
	
	/**
	 * Update.
	 *
	 * @param player the player
	 */
	public void update(Supplier supplier){		
		supplierRepository.save(supplier);
	}

	public void acceptSupplierRequest(Long idPlayer) {
		Supplier supplier = supplierRepository.findOne(idPlayer);
		supplier.setIsValid(true);
		supplierRepository.save(supplier);
	}


}
