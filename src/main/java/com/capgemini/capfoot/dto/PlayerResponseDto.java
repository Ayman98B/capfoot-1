package com.capgemini.capfoot.dto;

import com.capgemini.capfoot.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDto {

	private Long id;

	private String fullName;

	private String cin;

	private String phone;

	private String emailAddress;

	private boolean isStartingPlayer;

	private boolean isCaptain;

	private boolean isAvailable;

	private Long teamId;

	public static PlayerResponseDto createPlayerDto(Player player) {
		return new PlayerResponseDto(
				player.getId(), 
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

}
