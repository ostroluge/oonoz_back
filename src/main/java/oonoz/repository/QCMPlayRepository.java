package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.QCMPlay;

/**
 * The Interface QCMPlayRepository.
 */
public interface QCMPlayRepository extends CrudRepository<QCMPlay, Long> {

	/**
	 * Find by id qcm.
	 *
	 * @param idQcm the id qcm
	 * @return the list
	 */
	List<QCMPlay> findByIdQcm(long idQcm);
}
