package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	List<Admin> findAll();
}
