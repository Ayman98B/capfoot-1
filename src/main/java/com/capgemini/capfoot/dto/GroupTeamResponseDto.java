package com.capgemini.capfoot.dto;

import com.capgemini.capfoot.entity.GroupTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupTeamResponseDto {
	private Long id;
	private int nbDrawMatch;
	private int nbWonMatch;
	private int nbLossMatch;
	private int cumulPoint;
	private Long groupId;
	private Long teamId;

	public static GroupTeamResponseDto createGroupTeamResponseDto(GroupTeam groupTeam) {
		return new GroupTeamResponseDto(
				groupTeam.getId(), 
				groupTeam.getNbDrawMatch(), 
				groupTeam.getNbWonMatch(),
				groupTeam.getNbLossMatch(), 
				groupTeam.getCumulPoint(), 
				groupTeam.getGroup().getId(),
				groupTeam.getTeam().getId()
				);
	}
}
