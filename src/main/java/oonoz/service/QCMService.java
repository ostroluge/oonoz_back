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
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public List<QCM> findAll() {
		return QCMManager.getAllQCMs();
	}
}
