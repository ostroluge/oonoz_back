package oonoz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import oonoz.domain.QCM;
import oonoz.domain.Theme;

/**
 * The Interface QCMRepository.
 */
public interface QCMRepository extends CrudRepository<QCM, Long>, JpaSpecificationExecutor<QCM> {

	/**
	 * Find by id supplier.
	 *
	 * @param idSupplier
	 *            the id supplier
	 * @return the list
	 */
	List<QCM> findByIdSupplier(long idSupplier);

	/**
	 * Find by theme.
	 *
	 * @param theme
	 *            the theme
	 * @return the list
	 */
	List<QCM> findByTheme(Theme theme);

	/**
	 * Find by id supplier and id theme.
	 *
	 * @param idSupplier
	 *            the id supplier
	 * @param idTheme
	 *            the id theme
	 * @return the list
	 */
	List<QCM> findByIdSupplierAndIdTheme(long idSupplier, long idTheme);

	/**
	 * Find by is validated true and is complete true.
	 *
	 * @return the list
	 */
	List<QCM> findByIsValidatedTrueAndIsCompleteTrue();
	
	/**
	 * Find by id and validated true and is complete true.
	 *
	 * @param id the id
	 * @return the qcm
	 */
	QCM findByIdAndIsValidatedTrueAndIsCompleteTrue(long id); 
	/**
	 * Find by is validated false and is complete true.
	 *
	 * @return the list
	 */
	List<QCM> findByIsValidatedFalseAndIsCompleteTrue();

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the qcm
	 */
	QCM findByName(String name);

}
