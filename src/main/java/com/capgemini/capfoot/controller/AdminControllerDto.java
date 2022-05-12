package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.dto.*;
import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/dto/")
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
	AdminService adminService;

	@GetMapping("matchs/all")
	public ResponseEntity<List<MatchResponseDto>> getAllMatchsDto() {
		List<MatchResponseDto> matchDisputeeDtos = new ArrayList<MatchResponseDto>();
		for (MatchDisputee matchDisputee : matchService.getAllMatchs()) {
			matchDisputeeDtos.add(MatchResponseDto.createMatchDisputeeDto(matchDisputee));
		}
		return ResponseEntity.ok(matchDisputeeDtos);
	}

	@PostMapping("matchs/add")
	public ResponseEntity<MatchResponseDto> createMatchDto(@RequestBody MatchDisputee matchDisputee) {

		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.addMatch(matchDisputee)));
	}

	@PostMapping("teams/add")
	public ResponseEntity<TeamResponseDto> createTeamDto(@RequestBody Team createdTeam) {
		return ResponseEntity.ok(TeamResponseDto.createTeamDto(teamService.addTeam(createdTeam)));
	}

	@PutMapping("matchs/teams/{id}")
	public ResponseEntity<MatchResponseDto> setTeamsDto(@PathVariable(value = "id") Long id,
			@RequestBody MatchDisputee setTeams) {
		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.setTeams(id, setTeams)));
	}

	@PutMapping("matchs/score/{id}")
	public ResponseEntity<MatchResponseDto> updateMatchScoreDto(@PathVariable(value = "id") Long id,
			@RequestBody MatchDisputee updateTeamsScore) {
		return ResponseEntity.ok(
				MatchResponseDto.createMatchDisputeeDto(matchService.updateMatchScore(id, updateTeamsScore)));

	}

	@PutMapping("matchs/finalscore/{id}")
	public ResponseEntity<MatchResponseDto> updateMatchFinalScoreDto(@PathVariable("id") Long id,
			@RequestBody MatchDisputee matchDisputee) {
		return ResponseEntity.ok(
				MatchResponseDto.createMatchDisputeeDto(matchService.updateMatchFinalScore(id, matchDisputee)));

	}

	@GetMapping("matchs/{id}")
	public ResponseEntity<MatchResponseDto> getMatchByIdDto(@PathVariable("id") Long id) {
		return ResponseEntity.ok(MatchResponseDto.createMatchDisputeeDto(matchService.getMatchById(id)));

	}

	@GetMapping("championships/getall")
	public ResponseEntity<List<ChampionshipResponseDto>> getAllChampionshipDto() {
		List<ChampionshipResponseDto> championshipResponseDtos = new ArrayList<ChampionshipResponseDto>();
		for (Championship championship : championshipService.getAllChampionships()) {
			championshipResponseDtos.add(ChampionshipResponseDto.createChampionshipResponseDto(championship));
		}
		return ResponseEntity.ok(championshipResponseDtos);

	}

	@GetMapping("championships/{id}")
	public ResponseEntity<ChampionshipResponseDto> getChampionship(@PathVariable("id") long id) {

		return ResponseEntity
				.ok(ChampionshipResponseDto.createChampionshipResponseDto(championshipService.getChampionshipById(id)));
	}

	@PutMapping("championships/update/")
	public ResponseEntity<String> updateChampionship(@RequestBody ChampionshipCreationDto championshipupdateDto) {
		Championship champ = new Championship();
		BeanUtils.copyProperties(championshipupdateDto, champ);
		championshipService.updateChampionship(champ);
		return ResponseEntity.ok("update Championship");

	@PostMapping("championships/add")
	public ResponseEntity<ChampionshipResponseDto> createChampionshipDto(
			@RequestBody ChampionshipCreationDto championshipDto) {
		Admin admin = adminService.getAdminById(championshipDto.getAdminId());
		Championship champ = ChampionshipCreationDto.transferToChampionship(championshipDto, admin);
		return ResponseEntity.ok(
				ChampionshipResponseDto.createChampionshipResponseDto(championshipService.createChampionship(champ)));
	}

	@PutMapping("championships/update")
	public ResponseEntity<ChampionshipResponseDto> updateChampionship(
			@RequestBody ChampionshipUpdateDto championshipDto) {
		Admin admin = adminService.getAdminById(championshipDto.getAdminId());
		Championship champ = ChampionshipUpdateDto.transferToChampionship(championshipDto, admin);

		return ResponseEntity.ok(
				ChampionshipResponseDto.createChampionshipResponseDto(championshipService.updateChampionship(champ)));
	}

	@DeleteMapping("championships/delete/{id}")
	public ResponseEntity<String> deleteChampionship(@PathVariable("id") Long id) {
		championshipService.deleteChampionship(id);
		return ResponseEntity.ok("Championship has been deleted !");
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
