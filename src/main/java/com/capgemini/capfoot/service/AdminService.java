package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin);

	public Admin getAdminById(Long id);

}
