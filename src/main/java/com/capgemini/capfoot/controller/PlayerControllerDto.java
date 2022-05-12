package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.dto.PlayerCreationDto;
import com.capgemini.capfoot.dto.PlayerResponseDto;
import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.PlayerService;
import com.capgemini.capfoot.service.TeamService;

@RestController
@RequestMapping("/api/v2/players")
@CrossOrigin(origins = "*")
public class PlayerControllerDto {

	@Autowired
	PlayerService playerService;

	@Autowired
	TeamService teamService;

	@GetMapping("/all")
	public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
		List<PlayerResponseDto> playerResponseDtos = new ArrayList<PlayerResponseDto>();
		for (Player player : playerService.getAllPlayers()) {
			playerResponseDtos.add(PlayerResponseDto.createPlayerDto(player));
		}
		return ResponseEntity.ok(playerResponseDtos);
	}

	@PostMapping("/addPlayer")
	public ResponseEntity<PlayerResponseDto> addPlayer(@RequestBody PlayerCreationDto playerDto) {

		Team team = teamService.getTeamById(playerDto.getTeamId());
		return ResponseEntity.ok(PlayerResponseDto
				.createPlayerDto(playerService.addPlayer(PlayerCreationDto.transferToChampionship(playerDto, team))));
	}

}
