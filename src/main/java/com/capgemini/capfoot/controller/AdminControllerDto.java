package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.dto.ChampionshipCreationDto;
import com.capgemini.capfoot.dto.ChampionshipResponseDto;
import com.capgemini.capfoot.dto.MatchDisputeeResponseDto;
import com.capgemini.capfoot.dto.TeamResponseDto;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.GroupService;
import com.capgemini.capfoot.service.MatchService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/dto")
@CrossOrigin(origins = "*")
public class AdminControllerDto {

	@Autowired
	MatchService matchService;

	@Autowired
	TeamService teamService;

	@Autowired
	ChampionshipService championshipService;

	@Autowired
	GroupService groupService;

	@Autowired
	GroupRepository groupRepository;

	@GetMapping("matchs/all")
	public ResponseEntity<List<MatchDisputeeResponseDto>> getAllMatchsDto() {
		List<MatchDisputeeResponseDto> matchDisputeeDtos = new ArrayList<MatchDisputeeResponseDto>();
		for (MatchDisputee matchDisputee : matchService.getAllMatchs()) {
			matchDisputeeDtos.add(MatchDisputeeResponseDto.createMatchDisputeeDto(matchDisputee));
		}
		return ResponseEntity.ok(matchDisputeeDtos);
	}

	@PostMapping("matchs/add")
	public ResponseEntity<MatchDisputeeResponseDto> createMatchDto(@RequestBody MatchDisputee matchDisputee) {

		return ResponseEntity.ok(MatchDisputeeResponseDto.createMatchDisputeeDto(matchService.addMatch(matchDisputee)));
	}

	@PostMapping("teams/add")
	public ResponseEntity<TeamResponseDto> createTeamDto(@RequestBody Team createdTeam) {
		return ResponseEntity.ok(TeamResponseDto.createTeamDto(teamService.addTeam(createdTeam)));
	}

	@PutMapping("matchs/teams/{id}")
	public ResponseEntity<MatchDisputeeResponseDto> setTeamsDto(@PathVariable(value = "id") Long id,
			@RequestBody MatchDisputee setTeams) {
		return ResponseEntity.ok(MatchDisputeeResponseDto.createMatchDisputeeDto(matchService.setTeams(id, setTeams)));
	}

	@PutMapping("matchs/score/{id}")
	public ResponseEntity<MatchDisputeeResponseDto> updateMatchScoreDto(@PathVariable(value = "id") Long id,
			@RequestBody MatchDisputee updateTeamsScore) {
		return ResponseEntity.ok(
				MatchDisputeeResponseDto.createMatchDisputeeDto(matchService.updateMatchScore(id, updateTeamsScore)));

	}

	@PutMapping("matchs/finalscore/{id}")
	public ResponseEntity<MatchDisputeeResponseDto> updateMatchFinalScoreDto(@PathVariable("id") Long id,
			@RequestBody MatchDisputee matchDisputee) {
		return ResponseEntity.ok(
				MatchDisputeeResponseDto.createMatchDisputeeDto(matchService.updateMatchFinalScore(id, matchDisputee)));

	}

	@GetMapping("matchs/{id}")
	public ResponseEntity<MatchDisputeeResponseDto> getMatchByIdDto(@PathVariable("id") Long id) {
		return ResponseEntity.ok(MatchDisputeeResponseDto.createMatchDisputeeDto(matchService.getMatchById(id)));

	}

	@GetMapping("championships/getall")
	public ResponseEntity<List<ChampionshipResponseDto>> getAllChampionshipDto() {
		List<ChampionshipResponseDto> championshipResponseDtos = new ArrayList<ChampionshipResponseDto>();
		for (Championship championship : championshipService.getAllChampionships()) {
			championshipResponseDtos.add(ChampionshipResponseDto.createChampionshipResponseDto(championship));
		}
		return ResponseEntity.ok(championshipResponseDtos);

	}

	@PostMapping("championships/add")
	public ResponseEntity<String> createChampionshipDto(@RequestBody ChampionshipCreationDto championshipDto) {
		Championship champ = new Championship();
		BeanUtils.copyProperties(championshipDto, champ);
		championshipService.createChampionship(champ);
		return ResponseEntity.ok("createChampionship");
	}

	@PutMapping("championships/update/{idChampion}")
	public ResponseEntity<String> updateChampionship(@PathVariable("idChampion") Long idChampion,
			@RequestBody Championship championship) {
		championshipService.updateChampionship(championship);
		return ResponseEntity.ok("createChampionship");
	}

	@DeleteMapping("championships/delete/{id}")
	public void /* ResponseEntity<ChampionshipResponseDto> */ deleteChampionship(@PathVariable("id") Long id) {
		championshipService.deleteChampionship(id);
		/*
		 * return ResponseEntity.ok(
		 * ChampionshipResponseDto.createChampionshipResponseDto(championshipService.
		 * deleteChampionship(id)) );
		 */
	}

	@RequestMapping(value = "/admin_auth", method = RequestMethod.GET)
	public ResponseEntity<String> getAdmin() {
		return ResponseEntity.ok("Hello Admin");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "Au revoir";
	}

}
