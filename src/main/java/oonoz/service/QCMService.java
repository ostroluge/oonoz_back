package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.util.CheckQCMInformation;

/**
 * The Class QCMService.
 */
@Service
public class QCMService {
	
	/** The QCM manager. */
	@Autowired
	private QCMManagerImpl QCMManager;
	
	@Autowired
	private CheckQCMInformation checkQCMInformation;
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public List<QCM> findAll() {
		return QCMManager.getAllQCMs();
	}

	/**
	 * Post QCM.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 */
	public QCM postQCM(QCM qcm) throws WrongInformationException {
		
		checkQCMInformation.checkName(qcm.getName());
		checkQCMInformation.checkDescription(qcm.getDescription());
		checkQCMInformation.checkCategory(qcm.getCategory());
		
		return QCMManager.postQCM(qcm);
	}
}
