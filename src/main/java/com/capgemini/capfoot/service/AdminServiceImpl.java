package com.capgemini.capfoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.exception.AdminNotFoundException;
import com.capgemini.capfoot.repository.AdminRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin createAdmin(Admin admin) {
		log.info("Entred create admin");
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(Long id) {
		log.info("Entred get admin by id");
		if (adminRepository.findById(id).isEmpty()) {
			log.error("Admin not found !");
			throw new AdminNotFoundException(id);
		} else {
			return adminRepository.findById(id).get();
		}
	}

}
