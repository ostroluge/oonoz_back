package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.QCM;
import oonoz.domain.Theme;

/**
 * The Interface QCMRepository.
 */
public interface QCMRepository extends CrudRepository<QCM, Long> {

	
	List<QCM> findByIdSupplier(long idSupplier);

	List<QCM> findByTheme(Theme theme);
	
	List<QCM> findByIdSupplierAndIdTheme(long idSupplier,long idTheme);
	
//	List<QCM> findBySubTheme(String subTheme);
	
	List<QCM> findByIsValidatedTrueAndIsCompleteTrue();
	
	List<QCM> findByIsValidatedFalseAndIsCompleteTrue();
}
