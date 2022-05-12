package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.capfoot.dto.PlayerResponseDto;
import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.service.PlayerServiceImpl;

@RestController
@RequestMapping("/api/v1/players/dto")
@CrossOrigin(origins = "*")
public class PlayerControllerDto {

	@Autowired
	private PlayerServiceImpl playerService;

	@GetMapping("/all")
	public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
		List<PlayerResponseDto> playerResponseDtos = new ArrayList<PlayerResponseDto>();
		for (Player player : playerService.getAllPlayers()) {
			playerResponseDtos.add(PlayerResponseDto.createPlayerDto(player));
		}
		return ResponseEntity.ok(playerResponseDtos);
	}

	@PostMapping("addPlayer")
	public ResponseEntity<PlayerResponseDto> addPlayer(@RequestBody Player player) {
		return ResponseEntity.ok(
				PlayerResponseDto.createPlayerDto(
						playerService.addPlayer(player)
						)
				);
	}

}
