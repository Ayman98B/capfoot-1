package com.capgemini.capfoot.dto;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Groupe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupeResponseDto {

	private Long id;
	private String name;
	private int nbTeams;

	private Long championshipId;

	private List<GroupTeamResponseDto> groupTeams;

	public static GroupeResponseDto createGroupeDto(Groupe groupe) {
		return new GroupeResponseDto(
				groupe.getId(), 
				groupe.getName(), 
				groupe.getNbTeams(), 
				groupe.getChampionship().getId(),
				createGroupeTeamsDtos(groupe.getGroupTeams())
				);
	}

	public static List<GroupTeamResponseDto> createGroupeTeamsDtos(List<GroupTeam> groupTeams) {
		List<GroupTeamResponseDto> groupTeamsDtos = new ArrayList<GroupTeamResponseDto>();
		for (GroupTeam groupTeam : groupTeams) {
			groupTeamsDtos.add(GroupTeamResponseDto.createGroupTeamResponseDto(groupTeam));
		}
		return groupTeamsDtos;
	}

}
