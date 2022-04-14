package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupeController {

    @Autowired
    GroupService groupeService;

    @PostMapping("/ajouter/groupes")
    public void ajouterGroupe(@RequestBody Groupe groupe){
        groupeService.add(groupe);
    }

    @PostMapping("/supprimer/groupes")
    public void supprimerGroupe(@PathVariable Long id){
        groupeService.delete(id);
    }

    @PostMapping("/modifier/groupes")
    public void modifierGroupe(@RequestBody Groupe groupe, @PathVariable Long id){
        groupeService.update(Optional.ofNullable(groupe),id);
    }

    @GetMapping("/groupes")
    public List<Groupe> findAll(){
        return groupeService.findAll();
    }

    @GetMapping("/groupes/{id}")
    public Optional<Groupe> trouver(@PathVariable("id") Long id){
        return groupeService.findById(id);
    }
}
