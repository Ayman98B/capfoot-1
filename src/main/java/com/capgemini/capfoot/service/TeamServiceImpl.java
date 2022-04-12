package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team addTeem(Team team) {
        team.setNbPlayers(team.getPlayers().size());
        if(team.getNbPlayers() < 7 && team.getNbPlayers() > 7) {
            System.out.println("please your Team should have Only 7 players");
        }
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamByName(String name) {
        if(teamRepository.findByName(name) == null) return new Team();
        return teamRepository.findByName(name);
    }

    @Override
    public List<Team> gatAllTeam() {
        if (teamRepository.findAll().isEmpty()) return new ArrayList<>();
        return teamRepository.findAll();
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
        teamRepository.delete(toDelete.get());
        return true;
    }
}
