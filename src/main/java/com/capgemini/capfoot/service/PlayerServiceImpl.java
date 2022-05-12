package com.capgemini.capfoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.exception.PlayerNotFoundException;
import com.capgemini.capfoot.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepo;

	public PlayerServiceImpl(PlayerRepository playerRepo) {
		this.playerRepo = playerRepo;
	}

	@Override
	public Player addPlayer(Player player) {
		log.info("Entred add player !!");
		return playerRepo.save(player);
	}

	@Override
	public Player updatePlayer(Player player) {
		log.info("Entred update player !!");
		return playerRepo.save(player);
	}

	@Override
	public void deletePlayer(Long id) {
		log.info("Entred delete player by id !!");
		playerRepo.deleteById(id);
	}

	@Override
	public List<Player> getAllPlayers() {
		log.info("Entred get all players !!");
		return playerRepo.findAll();

	}

	@Override
	public Player getPlayerById(Long id) {
		log.info("Entred get player by id !!");
		if (!playerRepo.findById(id).isPresent()) {
			log.error("player not found !");
			throw new PlayerNotFoundException(id);

		} else {
			log.info("player with id " + id + " found !");
			return playerRepo.findById(id).get();
		}
	}

}
