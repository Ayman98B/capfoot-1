package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupeRepository;

   /* @Override
    public void add(Groupe groupe) {
        groupeRepository.save(groupe);
    }*/

    @Override
    public void delete(Long id) {

        groupeRepository.deleteById(id);
    }

    @Override
    public Groupe update(Groupe groupe, Long id) {
        Optional<Groupe> groupe1 = groupeRepository.findById(id);
        if(groupe1.isPresent()){
            groupe1= Optional.ofNullable(groupe);
            groupeRepository.save(groupe1.get());
        }else System.out.println("Ce groupe n'existe pas");
        return groupe;
    }

    @Override
    public List<Groupe> findAll() {
        return groupeRepository.findAll();
    }

    @Override
    public Optional<Groupe> findById(Long id) {

        return groupeRepository.findById(id);
    }


    @Override
    public Groupe buildGroup(String groupName) {

        return new Groupe(groupName);
    }

}
