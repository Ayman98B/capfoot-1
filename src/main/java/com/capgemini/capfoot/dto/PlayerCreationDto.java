package com.capgemini.capfoot.dto;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreationDto {

	private String fullName;

	private String cin;

	private String phone;

	private String emailAddress;

	private boolean isStartingPlayer;

	private boolean isCaptain;

	private boolean isAvailable;

	private Long teamId;

	public static PlayerCreationDto createPlayerDto(Player player) {
		return new PlayerCreationDto(
				player.getFullName(),
				player.getCin(),
				player.getPhone(), 
				player.getEmailAddress(), 
				player.isStartingPlayer(), 
				player.isCaptain(),
				player.isAvailable(), 
				player.getTeam().getId()
				);
	}
	
	public static Player transferToChampionship(PlayerCreationDto playerDto, Team team) {
		Player player = new Player();
		player.setFullName(playerDto.getFullName());
		player.setCin(playerDto.getCin());
		player.setPhone(playerDto.getPhone());
		player.setEmailAddress(playerDto.getEmailAddress());
		player.setStartingPlayer(playerDto.isStartingPlayer());
		player.setCaptain(playerDto.isCaptain());
		player.setAvailable(playerDto.isAvailable());
		player.setTeam(team);
		return player;
	}

}
