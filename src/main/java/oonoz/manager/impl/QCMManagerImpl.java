package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.domain.Theme;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;
import oonoz.repository.ThemeRepository;

/**
 * The Class QCMManagerImpl.
 */
@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager {
	
	/** The player repository. */
	@Resource
	private QCMRepository QCMRepository;
	
	@Resource
	private ThemeRepository themeRepository;
	
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
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(long id) {
		QCMRepository.delete(id);
	}
	
	/**
	 * Save.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 */
	public QCM save(QCM qcm) {
		return QCMRepository.save(qcm);
	}
	
	/**
	 * Update the qcm.
	 *
	 * @param id the id
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public QCM update(long id, QCM qcm) throws QCMDoesNotExistException {
		
		QCM existingQCM = QCMRepository.findOne(id);
		if (existingQCM != null) {
			qcm.setSubThemes(existingQCM.getSubThemes());
			qcm.setQuestions(existingQCM.getQuestions());
			qcm.setSupplier(existingQCM.getSupplier());
			if (existingQCM.getIdTheme() == qcm.getIdTheme()) {
				qcm.setTheme(existingQCM.getTheme());
			} else {
				Theme newTheme = themeRepository.findOne(qcm.getIdTheme());
				qcm.setTheme(newTheme);
			}
			return QCMRepository.save(qcm);
		} else {
			throw new QCMDoesNotExistException("The qcm does not exist");
		}
	}
}
