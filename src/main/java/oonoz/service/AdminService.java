package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMValidationException;
import oonoz.manager.impl.QCMManagerImpl;

/**
 * The Class AdminService.
 * 
 * Description :
 */
@Service
public class AdminService {

	/** The qcm manager. */
	@Autowired
	private QCMManagerImpl qcmManager;
	
	/**
	 * Gets the all validated QCM.
	 *
	 * @return the all validated QCM
	 */
	public List<QCM> getAllValidatedQCM(){
		return qcmManager.getAllValidatedQCM();
	}
	
	/**
	 * Gets the all not validated QCM.
	 *
	 * @return the all not validated QCM
	 */
	public List<QCM> getAllNotValidatedQCM(){
		return qcmManager.getAllNotValidatedQCM();
	}
	
	/**
	 * Validate QCM.
	 *
	 * @param id the id
	 * @throws QCMValidationException 
	 * @throws QCMDoesNotExistException 
	 */
	public void validateQCM(long id) throws QCMDoesNotExistException, QCMValidationException{
		qcmManager.validateQCM(id);
	}

}
