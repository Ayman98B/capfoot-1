package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupeRepository;

    @Override
    public void add(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    @Override
    public void delete(Long id) {
        groupeRepository.deleteById(id);
    }

    @Override
    public void update(Groupe groupe, Long id) {
        Groupe modifiedgroupe = groupeRepository.getById(id);
        modifiedgroupe = groupe;
        groupeRepository.save(modifiedgroupe);
    }

    @Override
    public List<Groupe> findAll() {
        return groupeRepository.findAll();
    }

    @Override
    public Optional<Groupe> findById(Long id) {
        return groupeRepository.findById(id);
    }

}
