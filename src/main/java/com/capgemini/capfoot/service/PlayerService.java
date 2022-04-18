package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Player;

import java.util.List;

public interface PlayerService {

    public Player addPlayer(Player player);
    public Player updatePlayer(Player player);
    public void deletePlayer(Long id);
    public List<Player> getAllPlayers();
    public Player getPlayerById(Long id);
    public void addPlayerToTeam(Long idPlayer, Long idTeam);

}
