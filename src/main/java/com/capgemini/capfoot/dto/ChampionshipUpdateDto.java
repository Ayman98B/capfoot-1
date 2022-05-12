package com.capgemini.capfoot.dto;

import java.time.LocalDate;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampionshipUpdateDto {
	
	private Long id;

	private String label;

	private LocalDate startDate;

	private LocalDate endDate;

	private Championship_State statut;

	private boolean progress;
	
	private Long adminId;

	public static ChampionshipUpdateDto createChampionshipResponseDto(Championship championship) {
		return new ChampionshipUpdateDto(championship.getId() ,championship.getLabel(), championship.getStartDate(),
				championship.getEndDate(), championship.getStatut(), championship.isProgress(), championship.getAdmin().getId());
	}

	public static Championship transferToChampionship(ChampionshipUpdateDto championshipDto, Admin admin) {
		Championship champ = new Championship();
		champ.setId(championshipDto.getId());
		champ.setLabel(championshipDto.getLabel());
		champ.setStartDate(championshipDto.getStartDate());
		champ.setEndDate(championshipDto.getEndDate());
		champ.setStatut(championshipDto.getStatut());
		champ.setAdmin(admin);
		return champ;
	}

}
