package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    @Autowired
    private PlayerServiceImpl playerService;

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return players;
    }

    @PostMapping("addPlayer")
    public Player addPlayer(@RequestBody Player player) {
        Player newPlayer = playerService.addPlayer(player);
        return newPlayer;
    }

}
