package com.capgemini.capfoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.TeamServiceImpl;

@RestController
@RequestMapping("/api/v1/teams")
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;
   
    @GetMapping("/all")
    public List<Team> getAllTeams() {
        List<Team> teams = teamService.gatAllTeam();
        return teams;
    }

    @PostMapping("/inscription")
    public Team inscription(@RequestBody Team team) {
        return teamService.inscription(team);
    }

}
