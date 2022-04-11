package com.capgemini.capfoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.capfoot.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
}
