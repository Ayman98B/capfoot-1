package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Match;
import com.capgemini.capfoot.entity.Player;

import java.util.List;

public interface PlayerService {

    void addPlayer(Player player);
    public void updatePlayer(Player player,Long id);
    public void deletePlayer(Long id);
    public List<Player> getAllPlayers();
    public Player getPlayerById(Long id);
    public void addPlayerToTeam(Long idPlayer, Long idTeam);

}
