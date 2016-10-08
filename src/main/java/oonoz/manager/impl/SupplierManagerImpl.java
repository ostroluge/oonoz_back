package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.manager.SupplierManager;
import oonoz.repository.SupplierRepository;

@Service(value = "supplierManager")
public class SupplierManagerImpl implements SupplierManager {

	@Resource
	private SupplierRepository supplierRepository;
	
	
}
