package oonoz.back.services.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.back.domain.Admin;
import oonoz.back.repositories.AdminRepository;
import oonoz.back.services.AdminService;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {


		@Resource
		private AdminRepository adminRepository;
		
		@Override
		public Collection<Admin> getAllAdmin() {
			return this.adminRepository.findAll();
		}
}
