package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/championships")

public class ChampionshipController {
    @Autowired
    ChampionshipService championshipService;

    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable("id") Long id){
        championshipService.deleteChampionship(id);
    }
}
