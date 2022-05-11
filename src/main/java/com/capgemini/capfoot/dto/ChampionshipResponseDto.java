package com.capgemini.capfoot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.Groupe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChampionshipResponseDto {

	private Long id;

	private String label;

	private LocalDate startDate;

	private LocalDate endDate;

	private Championship_State statut;

	private boolean progress;

	private Long adminId;

	private List<GroupeResponseDto> groups;

	public static ChampionshipResponseDto createChampionshipResponseDto(Championship championship) {
		return new ChampionshipResponseDto(
				championship.getId(), 
				championship.getLabel(), 
				championship.getStartDate(),
				championship.getEndDate(), 
				championship.getStatut(), 
				championship.isProgress(),
				championship.getAdmin().getId(), 
				createGroupeTeamsDtos(championship.getGroups()));
	}

	public static List<GroupeResponseDto> createGroupeTeamsDtos(List<Groupe> group) {
		if (group == null) {
			return null;
		} else {
			List<GroupeResponseDto> groupDtos = new ArrayList<GroupeResponseDto>();
			for (Groupe groupTemp : group) {
				groupDtos.add(GroupeResponseDto.createGroupeDto(groupTemp));
			}
			return groupDtos;
		}
	}

}
