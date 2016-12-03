package oonoz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oonoz.manager.impl.QCMManagerImpl;

/**
 * The Class QCMService.
 */
@Service
public class QCMService {
	
	/** The QCM manager. */
	@Autowired
	private QCMManagerImpl QCMManager;
	
}
