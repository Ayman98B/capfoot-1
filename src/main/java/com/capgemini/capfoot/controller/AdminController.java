package com.capgemini.capfoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.service.ChampionshipService;

@RestController
@RequestMapping("/api/v1/championships")
public class AdminController {

	@Autowired
	ChampionshipService championshipService;

	@GetMapping
	public List<Championship> getAllChampionship() {
		return championshipService.getAllChampionships();
	}

	@PostMapping
	public void createChampionship(@RequestBody Championship championship) {
		championshipService.createChampionship(championship);
	}

	@PutMapping("/updateChampion/{idChampion}")
	public void updateChampionship(@ @RequestBody Championship championship) {
		championshipService.updateChampionship(idChampion,championship);
	}
  
    @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable("id") Long id){
        championshipService.deleteChampionship(id);
    }


}
