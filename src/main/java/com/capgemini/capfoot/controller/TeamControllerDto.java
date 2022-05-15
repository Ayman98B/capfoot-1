package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.dto.TeamResponseDto;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.TeamServiceImpl;

@RestController
@RequestMapping("/api/v1/teams/dto")
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
	public ResponseEntity<TeamResponseDto> inscription(@RequestBody Team team) {
		return ResponseEntity.ok(TeamResponseDto.createTeamDto(teamService.inscription(team)));
	}

	/*@GetMapping("/allByStage/")
	public ResponseEntity<List<TeamResponseDto>> getAllTeamsByStage() {
		List<TeamResponseDto> teamResponseDtos = new ArrayList<TeamResponseDto>();
		for (Team team : teamService.gatAllTeam()) {
			teamResponseDtos.add(TeamResponseDto.createTeamDto(team));
		}
		return ResponseEntity.ok(teamResponseDtos);
	}*/

}
