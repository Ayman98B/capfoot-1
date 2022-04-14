package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public void add(Admin admin);
    public void delete(Long id);
    public void update(Admin admin);

}
