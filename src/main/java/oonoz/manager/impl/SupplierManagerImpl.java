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

@Service(value = "supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	@Resource
	private SupplierRepository supplierRepository;

	@Resource
	private AuthoritiesRepository authoritiesRepository;

	public void create(Supplier supplier) throws PlayerAlreadyExistException {

		List<Player> suppliers = supplierRepository.findByUsernameOrMail(supplier.getUsername(), supplier.getMail());
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

}
