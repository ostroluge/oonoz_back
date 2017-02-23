package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.domain.QCMPlay;
import oonoz.dto.model.PlayerDto;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMPlayDoesNotExistException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.manager.impl.QCMPlayManagerImpl;

/**
 * The Class QCMPlayService.
 */
@Service
public class QCMPlayService {

	/** The qcm play manager. */
	@Autowired
	private QCMPlayManagerImpl qcmPlayManager;
	
	/** The qcm manager. */
	@Autowired
	private QCMManagerImpl qcmManager;
	
	/**
	 * Find winners.
	 *
	 * @param idQcm the id qcm
	 * @param minimumScore the minimum score
	 * @return the list
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public List<PlayerDto> findWinners(long idQcm, int minimumScore) throws QCMDoesNotExistException {
		QCM qcm = qcmManager.findOne(idQcm);
		if (qcm != null) {
			return qcmPlayManager.getQCMWinners(idQcm, minimumScore);
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQcm + " does not exist");
		}
	}

	/**
	 * Update QCM play.
	 *
	 * @param qcmPlay the qcm play
	 * @return the QCM play
	 * @throws QCMPlayDoesNotExistException the QCM play does not exist exception
	 */
	public QCMPlay updateQCMPlay(QCMPlay qcmPlay) throws QCMPlayDoesNotExistException {
		QCMPlay existingQCMPlay = qcmPlayManager.findOne(qcmPlay.getId());
		
		if (existingQCMPlay != null) {
			return qcmPlayManager.update(existingQCMPlay, qcmPlay);
		} else {
			throw new QCMPlayDoesNotExistException("The QCMPlay with id " + qcmPlay.getId() +
					" does not exist");
		}
	}
}
