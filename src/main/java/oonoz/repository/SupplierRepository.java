package oonoz.repository;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Supplier;

/**
 * The Interface SupplierRepository.
 * 
 * Description : Manage database access about Supplier entity
 */
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}
