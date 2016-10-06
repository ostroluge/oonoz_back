package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long>{

	List<Supplier> findAll();
}
