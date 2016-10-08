package oonoz.repository;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Admin;


/**
 * The Interface AdminRepository.
 * 
 * Description :
 * 		Manage database access about Admin entity
 */
public interface AdminRepository extends CrudRepository<Admin, Long>{


}
