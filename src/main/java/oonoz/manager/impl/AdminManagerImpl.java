package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.manager.AdminManager;
import oonoz.repository.AdminRepository;


/**
 * The Class AdminManagerImpl.
 * 
 * Description :
 * 		Manage the different technical operations about Admin entity.
 */
@Service(value = "adminManager")
public class AdminManagerImpl implements AdminManager {


		/** The admin repository. */
		@Resource
		private AdminRepository adminRepository;
		
	
}
