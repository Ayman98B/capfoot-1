package com.capgemini.capfoot.service;

import java.util.List;

import com.capgemini.capfoot.entity.Player;

public interface PlayerService {

    public Player addPlayer(Player player);
    public Player updatePlayer(Player player);
    public void deletePlayer(Long id);
    public List<Player> getAllPlayers();
    public Player getPlayerById(Long id);

}
