package com.capgemini.capfoot.dto;

import java.time.LocalDate;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampionshipUpdateDto {

	private String label;

	private LocalDate startDate;

	private LocalDate endDate;

	private Championship_State statut;

	private boolean progress;

	public static ChampionshipUpdateDto createChampionshipResponseDto(Championship championship) {
		return new ChampionshipUpdateDto(championship.getLabel(), championship.getStartDate(),
				championship.getEndDate(), championship.getStatut(), championship.isProgress());
	}

	public static Championship transferToChampionship(ChampionshipUpdateDto championshipDto) {
		Championship champ = new Championship();
		champ.setLabel(championshipDto.getLabel());
		champ.setStartDate(championshipDto.getStartDate());
		champ.setEndDate(championshipDto.getEndDate());
		champ.setStatut(championshipDto.getStatut());
		return champ;
	}

}
