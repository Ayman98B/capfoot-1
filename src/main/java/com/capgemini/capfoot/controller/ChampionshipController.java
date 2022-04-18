package com.capgemini.capfoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.service.ChampionshipService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/championships")
public class ChampionshipController {

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
  
  @DeleteMapping("/{id}")
    public void deleteChampionship(@PathVariable("id") Long id){
        championshipService.deleteChampionship(id);
    }


}
