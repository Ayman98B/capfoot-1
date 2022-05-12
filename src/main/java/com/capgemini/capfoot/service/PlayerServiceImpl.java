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
    @Autowired
    private TeamRepository teamRepo;

    public PlayerServiceImpl(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }


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

}

