package oonoz.back.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.back.domain.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long>{

	List<Supplier> findAll();
}
