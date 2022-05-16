package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.dto.ChampionshipCreationDto;
import com.capgemini.capfoot.dto.ChampionshipResponseDto;
import com.capgemini.capfoot.dto.ChampionshipUpdateDto;
import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.service.AdminService;
import com.capgemini.capfoot.service.ChampionshipService;

@RestController
@RequestMapping("/api/v2/championships")
@CrossOrigin(origins = "*")
public class ChampionshipControllerDto {

	@Autowired
	ChampionshipService championshipService;

	@Autowired
	AdminService adminService;

	@GetMapping("/getall")
	public ResponseEntity<List<ChampionshipResponseDto>> getAllChampionshipDto() {
		List<ChampionshipResponseDto> championshipResponseDtos = new ArrayList<ChampionshipResponseDto>();
		for (Championship championship : championshipService.getAllChampionships()) {
			championshipResponseDtos.add(ChampionshipResponseDto.createChampionshipResponseDto(championship));
		}
		return ResponseEntity.ok(championshipResponseDtos);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ChampionshipResponseDto> getChampionship(@PathVariable("id") long id) {

		return ResponseEntity
				.ok(ChampionshipResponseDto.createChampionshipResponseDto(championshipService.getChampionshipById(id)));
	}

	@PostMapping("/add")
	public ResponseEntity<ChampionshipResponseDto> createChampionshipDto(
			@RequestBody ChampionshipCreationDto championshipDto) {
		Admin admin = adminService.getAdminById(championshipDto.getAdminId());
		Championship champ = ChampionshipCreationDto.transferToChampionship(championshipDto, admin);
		return ResponseEntity.ok(
				ChampionshipResponseDto.createChampionshipResponseDto(championshipService.createChampionship(champ)));
	}

	@PutMapping("/update")
	public ResponseEntity<ChampionshipResponseDto> updateChampionship(
			@RequestBody ChampionshipUpdateDto championshipDto) throws MessagingException {
		Admin admin = adminService.getAdminById(championshipDto.getAdminId());
		Championship champ = ChampionshipUpdateDto.transferToChampionship(championshipDto, admin);

		return ResponseEntity.ok(
				ChampionshipResponseDto.createChampionshipResponseDto(championshipService.updateChampionship(champ)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteChampionship(@PathVariable("id") Long id) {
		championshipService.deleteChampionship(id);
		return ResponseEntity.ok("Championship has been deleted !");
	}
}
