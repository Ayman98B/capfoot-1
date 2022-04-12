package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Groupe;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    public void add(Groupe groupe);
    public void delete(Long id);
    public void update(Groupe groupe, Long id);
    public List<Groupe> findAll();
    public Optional<Groupe> findById(Long id);

}
