package com.capgemini.capfoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.service.PlayerServiceImpl;

@RestController
@RequestMapping("/api/v1/players")
@CrossOrigin(origins = "*")
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
