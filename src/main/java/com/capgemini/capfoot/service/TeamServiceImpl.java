package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.*;
import com.capgemini.capfoot.repository.ChampionshipRepo;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	ChampionshipRepo championshipRepo;

	@Autowired
	PlayerServiceImpl playerService;

	@Autowired

	PlayerRepository playerRepository;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public Team addTeam(Team team) {

		if (team.getPlayers().size() != 0 && team.getPlayers().size() != 7)
			System.out.println("Le nombre de joueurs par equipe doit etre egale à 7");
		return teamRepository.save(team);
	}
	
	@Override
	public Team getTeamById(Long idTeam) {
		return teamRepository.findById(idTeam).get();
	}

	@Override
	public Team getTeamByName(String name) {
		return teamRepository.findByName(name);
	}

	@Override
	public List<Team> gatAllTeam() {
		List<Team> teamList = teamRepository.findAll();
		if (teamList.isEmpty()) {
			System.out.println("No Teams Found!");
		}
		return teamList;
	}

	@Override
	public Team updateTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Boolean deleteTeam(Team team) {
		teamRepository.delete(team);
		return true;
	}

	@Override
	public Boolean deleteTeamById(Long id) {
		Optional<Team> toDelete = teamRepository.findById(id);
		if (toDelete.isPresent()) {
			teamRepository.delete(toDelete.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Team inscription(Team team) {
		Team newTeam = this.addTeam(team);
		List<Player> players = newTeam.getPlayers();
		for (Player p : players) {
			if (p.isAvailable())
				{
					p.setTeam(newTeam);
					p.setAvailable(false);
				}
			else
				System.out.println("Joueur n'est pas dispo");
		}
		playerRepository.saveAll(players);
		return newTeam;
	}

	@Override
	public List<Team> getAllTeamsByChampionat(Long idChamp) {
		Championship champ = championshipRepo.findById(idChamp).get();
		List<Groupe> groupes = champ.getGroups();
		Set<Team> teams = new HashSet<Team>();
		for (Groupe g : groupes) {
			List<GroupTeam> groupTeams = g.getGroupTeams();
			for (GroupTeam gt : groupTeams) {
				teams.add(gt.getTeam());
			}
		}
		return new ArrayList<>(teams);
	}

	@Override
	public List<Team> getAllTeamsByStage(Championship_State stage) {
		return teamRepository.findByStage(stage);
	}

}
