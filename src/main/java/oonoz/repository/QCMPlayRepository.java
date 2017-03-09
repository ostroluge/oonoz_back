package oonoz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	/**
	 * return number of qcm played by the player
	 *
	 * @param idPlayer the id player
	 * @return the int
	 */
    long countByIdPlayer(Long idPlayer);

    /**
	 * Find by id player.
	 *
	 * @param idQcm the id qcm
	 * @return the list
	 */
	List<QCMPlay> findByIdPlayer(long idPlayer);
	
}
