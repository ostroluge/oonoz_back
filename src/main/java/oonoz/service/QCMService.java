package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.manager.impl.QCMManagerImpl;

/**
 * The Class QCMService.
 */
@Service
public class QCMService {
	
	/** The QCM manager. */
	@Autowired
	private QCMManagerImpl QCMManager;
	
	
	public List<QCM> getSupplierQCM(long idSupplier){
		List<QCM> questionList = QCMManager.findSupplierQuestions(idSupplier);
		return questionList;
	}


	public List<QCM> searchSupplierQCM(String theme) {
		List<QCM> questionList = QCMManager.findSupplierQCMByTheme(theme);
		return questionList;
	}
	
}
