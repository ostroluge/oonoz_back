package oonoz.manager.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.domain.Admin;
import oonoz.manager.AdminManager;
import oonoz.repository.AdminRepository;

@Service(value = "adminManager")
public class AdminManagerImpl implements AdminManager {


		@Resource
		private AdminRepository adminRepository;
		
		@Override
		public Collection<Admin> getAllAdmin() {
			return this.adminRepository.findAll();
		}
}
