package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;

/**
 * The Class QCMManagerImpl.
 */
@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager{
	
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

	public List<QCM> findSupplierQuestions(long idSupplier) {
		return QCMRepository.findByIdSupplier(idSupplier);
	}

	public List<QCM> findSupplierQCMByTheme(String theme) {
		return QCMRepository.findByTheme(theme);
	}

}
