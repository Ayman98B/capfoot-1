package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepo;
    private TeamRepository teamRepo;

    @Override
    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }



    /*
    public Player updatePlayer(Player player, Long id) {
        Optional<Player> p = playerRepo.findById(id);
        p.get().setFirstName(player.getFirstName());
        p.get().setLastName(player.getLastName());
        p.get().setCIN(player.getCIN());
        p.get().setEmailAddress(player.getEmailAddress());
        p.get().setPassword(player.getPassword());
        p.get().setPhone(player.getPhone());
        p.get().setCaptain(player.isCaptain());
        p.get().setStartingPlayer(player.isStartingPlayer());
        p.get().setAvailable(player.isAvailable());
        return playerRepo.save(p.get());
    } */
    @Override
    public Player updatePlayer(Player player)
    {
        return playerRepo.save(player);
    }



    @Override
    public void deletePlayer(Long id) {
        Optional<Player> p = playerRepo.findById(id);
        playerRepo.delete(p.get());
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = playerRepo.findAll();
        return players;

    }

    @Override
    public Player getPlayerById(Long id) {
        Optional<Player> player = playerRepo.findById(id);
        return player.get();
    }

    @Override
    public void addPlayerToTeam(Long idPlayer, Long idTeam) {
        Optional<Player> player = playerRepo.findById(idPlayer);
        Optional<Team> team = teamRepo.findById(idTeam);

        if( (player.get().isAvailable()) && (team.get().getNbPlayers() < 7)) {
            team.get().getplayers().add(player.get());
            player.get().setTeam(team.get());
            player.get().setAvailable(false);
            playerRepo.save(player.get());
            teamRepo.save(team.get());
        }
        else
            System.out.println("Joueur n'est pas dispo");
    }
}
