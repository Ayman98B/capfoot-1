package com.capgemini.capfoot.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;

public interface GroupService {

    //public void add(Groupe groupe);
    public void delete(Long id);
    public Groupe update(Groupe groupe, Long id);
    public List<Groupe> findAll();
    public Optional<Groupe> findById(Long id);
    public Groupe buildGroup(String groupName, Championship championship);

}
