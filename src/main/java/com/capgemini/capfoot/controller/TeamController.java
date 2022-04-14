package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.PlayerServiceImpl;
import com.capgemini.capfoot.service.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private PlayerServiceImpl playerService;

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
