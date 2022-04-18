package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.repository.AdminRepository;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void add(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void update(Admin admin) {

    }

}
