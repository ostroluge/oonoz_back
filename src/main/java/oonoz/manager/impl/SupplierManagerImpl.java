package oonoz.manager.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.domain.Supplier;
import oonoz.manager.SupplierManager;
import oonoz.repository.SupplierRepository;

@Service(value = "supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	@Resource
	private SupplierRepository supplierRepository;
	
	@Override
	public Collection<Supplier> getAllSupplier() {
		return this.supplierRepository.findAll();
	}
	
}
