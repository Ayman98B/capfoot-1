package com.capgemini.capfoot.dto;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.capfoot.entity.GroupTeam;
import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseDto {
	private Long id;

	private String name;

	private Site site;

	private List<PlayerResponseDto> players;

	private int nbPlayers;

	private List<GroupTeamResponseDto> groupTeam;

	public static TeamResponseDto createTeamDto(Team team) {
		return new TeamResponseDto(
				team.getId(), 
				team.getName(), 
				team.getSite(), 
				createPlayerDtos(team.getPlayers()),
				team.getNbPlayers(), 
				createGroupTeamResponseDto(team.getGroupTeam()));
	}

	public static List<PlayerResponseDto> createPlayerDtos(List<Player> players) {
		List<PlayerResponseDto> playerDtos = new ArrayList<PlayerResponseDto>();
		for (Player player : players) {
			playerDtos.add(PlayerResponseDto.createPlayerDto(player));
		}
		return playerDtos;
	}

	public static List<GroupTeamResponseDto> createGroupTeamResponseDto(List<GroupTeam> groupTeams) {
		List<GroupTeamResponseDto> groupTeamResponseDtos = new ArrayList<GroupTeamResponseDto>();
		for (GroupTeam groupTeam : groupTeams) {
			groupTeamResponseDtos.add(GroupTeamResponseDto.createGroupTeamResponseDto(groupTeam));
		}
		return groupTeamResponseDtos;
	}

}
