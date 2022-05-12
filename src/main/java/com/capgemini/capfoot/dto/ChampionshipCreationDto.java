package com.capgemini.capfoot.dto;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampionshipCreationDto {

	private String label;

	private LocalDate startDate;

	private LocalDate endDate;

	private Championship_State statut;

	private boolean progress;

	// private List<Groupe> groups;

	private Long adminId;

	public static ChampionshipCreationDto createChampionshipResponseDto(Championship championship) {
		return new ChampionshipCreationDto(
				championship.getLabel(), 
				championship.getStartDate(),
				championship.getEndDate(), 
				championship.getStatut(), 
				championship.isProgress(),
				championship.getAdmin().getId());
	}

}
