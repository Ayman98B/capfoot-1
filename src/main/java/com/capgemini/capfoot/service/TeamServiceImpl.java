package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    private PlayerServiceImpl playerService;
    @Override
    public Team addTeam(Team team) {

        if(team.getPlayers().size() != 0 || team.getPlayers().size() != 7)
            System.out.println("Le nombre de joueurs par equipe doit etre egale à 7");
        return teamRepository.save(team);
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
        List<Player> players = team.getPlayers();
        for (Player p : players) {
            Player player = playerService.addPlayer(p);
            playerService.addPlayerToTeam(newTeam.getId(),player.getId());
        }

        return newTeam;
    }


}
