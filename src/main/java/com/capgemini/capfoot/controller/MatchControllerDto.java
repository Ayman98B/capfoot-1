package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.dto.MatchResponseDto;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.service.MatchService;

@RestController
@RequestMapping("/api/v2/matches")
@CrossOrigin(origins = "*")
public class MatchControllerDto {

	@Autowired
	MatchService matchService;
	
	@GetMapping("/all")
	public ResponseEntity<List<MatchResponseDto>> getAllMatchsDto() {
		List<MatchResponseDto> matchDisputeeDtos = new ArrayList<MatchResponseDto>();
		for (MatchDisputee matchDisputee : matchService.getAllMatchs()) {
			matchDisputee.getTeamAway().setPlayers(null);
            matchDisputee.getTeamHome().setPlayers(null);
            matchDisputee.getTeamAway().setGroupTeam(null);
            matchDisputee.getTeamHome().setGroupTeam(null);
			matchDisputeeDtos.add(MatchResponseDto.createMatchDisputeeDto(matchDisputee));
		}
		return ResponseEntity.ok(matchDisputeeDtos);
	}

	@PostMapping("/add")
	public ResponseEntity<MatchResponseDto> createMatchDto(@RequestBody MatchDisputee matchDisputee) {

		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.addMatch(matchDisputee)));
	}

	@PutMapping("/teams/{id}")
	public ResponseEntity<MatchResponseDto> setTeamsDto(@PathVariable(value = "id") Long id,
														@RequestBody MatchDisputee setTeams) {
		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.setTeams(id, setTeams)));
	}

	@PutMapping("/score/{id}")
	public ResponseEntity<MatchResponseDto> updateMatchScoreDto(@PathVariable(value = "id") Long id,
																@RequestBody MatchDisputee updateTeamsScore) {
		return ResponseEntity.ok(
				MatchResponseDto.createMatchDisputeeDto(matchService.updateMatchScore(id, updateTeamsScore)));

	}

	@PutMapping("/finalscore/{id}")
	public ResponseEntity<MatchResponseDto> updateMatchFinalScoreDto(@PathVariable("id") Long id,
																	 @RequestBody MatchDisputee matchDisputee) {
		return ResponseEntity.ok(
				MatchResponseDto.createMatchDisputeeDto(matchService.updateMatchFinalScore(id, matchDisputee)));

	}

	@GetMapping("/{id}")
	public ResponseEntity<MatchResponseDto> getMatchByIdDto(@PathVariable("id") Long id) {
		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.getMatchById(id)));
	}
}
