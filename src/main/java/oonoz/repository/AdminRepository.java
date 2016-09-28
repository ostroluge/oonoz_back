package oonoz.back.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.back.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{

	List<Admin> findAll();
}
