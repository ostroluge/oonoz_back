package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;

/**
 * The Class QCMManagerImpl.
 */
@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager {
	
	/** The player repository. */
	@Resource
	private QCMRepository QCMRepository;
	
	/**
	 * Gets the all the qcm.
	 *
	 * @return all the qcm
	 */
	public List<QCM> getAllQCMs(){
		return (List<QCM>) QCMRepository.findAll();
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the qcm
	 */
	public QCM findOne(long id) {
		return QCMRepository.findOne(id);
	}
	
	/**
	 * Post QCM.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 */
	public QCM postQCM(QCM qcm) {
		return QCMRepository.save(qcm);
	}
}
