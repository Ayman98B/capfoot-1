package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public void ajouter(Admin admin);
    public void supprimer(Long id);
    public void modifier(Admin admin);
    public List<Admin> trouverT();
    public Optional<Admin> trouverParId(Long id);

}
