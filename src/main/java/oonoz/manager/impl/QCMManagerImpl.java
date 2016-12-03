package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.manager.QCMManager;
import oonoz.repository.QCMRepository;

@Component(value = "QCMManager")
public class QCMManagerImpl implements QCMManager{
	
	/** The player repository. */
	@Resource
	private QCMRepository QCMRepository;
	
	public List<QCM> getAllQCMs(){
		return (List<QCM>) QCMRepository.findAll();
	}

}
