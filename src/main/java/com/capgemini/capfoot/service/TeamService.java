package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Team;

public interface TeamService {

	Team addTeam(Team team);
	
	Team getTeamById(Long idTeam);

	Team getTeamByName(String name);

	List<Team> gatAllTeam();

	Team updateTeam(Team team);

	Boolean deleteTeam(Team team);

	Boolean deleteTeamById(Long id);

	Team inscription(Team team);

	List<Team> getAllTeamsByChampionat(Long idChamp);

	List<Team> getAllTeamsByStage(Championship_State stage);

}
