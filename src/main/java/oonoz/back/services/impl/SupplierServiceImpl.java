package oonoz.back.services.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.back.domain.Supplier;
import oonoz.back.repositories.SupplierRepository;
import oonoz.back.services.SupplierService;

@Service(value = "supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Resource
	private SupplierRepository supplierRepository;
	
	@Override
	public Collection<Supplier> getAllSupplier() {
		return this.supplierRepository.findAll();
	}

	
}
