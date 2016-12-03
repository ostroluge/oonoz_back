package oonoz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oonoz.manager.impl.QCMManagerImpl;

@Service
public class QCMService {
	
	@Autowired
	private QCMManagerImpl QCMManager;
	
	

}
