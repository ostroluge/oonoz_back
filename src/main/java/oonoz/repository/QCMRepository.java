package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.QCM;

/**
 * The Interface QCMRepository.
 */
public interface QCMRepository extends CrudRepository<QCM, Long> {

	
	List<QCM> findByIdSupplier(long idSupplier);

	List<QCM> findByTheme(String theme);
	
	List<QCM> findByIsValidatedTrueAndIsCompleteTrue();
	
	List<QCM> findByIsValidatedFalseAndIsCompleteTrue();
}
