package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.capfoot.dto.TeamResponseDto;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.TeamServiceImpl;

@RestController
@RequestMapping("/api/v2/teams")
@CrossOrigin(origins = "*")
public class TeamControllerDto {

	@Autowired
	private TeamServiceImpl teamService;

	@GetMapping("/all")
	public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
		List<TeamResponseDto> teamResponseDtos = new ArrayList<TeamResponseDto>();
		for (Team team : teamService.gatAllTeam()) {
			teamResponseDtos.add(TeamResponseDto.createTeamDto(team));
		}
		return ResponseEntity.ok(teamResponseDtos);
	}

	@PostMapping("/inscription")
	public ResponseEntity<TeamResponseDto> inscription(@RequestBody Team team) throws MessagingException {
		return ResponseEntity.ok(TeamResponseDto.createTeamDto(teamService.inscription(team)));
	}

}
