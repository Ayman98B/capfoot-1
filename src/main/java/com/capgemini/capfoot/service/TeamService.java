package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Team;

import java.util.List;

public interface TeamService {

     Team addTeem(Team team);
     Team getTeamByName(String name);
     List<Team> gatAllTeam();
     Team updateTeam (Team team);
     Boolean deleteTeam(Team team);
     Boolean deleteTeamById(Long id);


}
