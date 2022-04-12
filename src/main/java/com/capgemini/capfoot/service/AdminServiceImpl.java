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
    public void ajouter(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void supprimer(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void modifier(Admin admin) {

    }

    @Override
    public List<Admin> trouverT() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> trouverParId(Long id) {
        return adminRepository.findById(id);
    }
}
