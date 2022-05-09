package com.capgemini.capfoot.dto;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.capfoot.entity.Admin;
import com.capgemini.capfoot.entity.Championship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String emailAdress;

	private List<ChampionshipResponseDto> championships;

	public static AdminResponseDto createAdminResponseDto(Admin admin) {
		return new AdminResponseDto(
				admin.getId(), 
				admin.getFirstName(), 
				admin.getLastName(), 
				admin.getEmailAdress(),
				createChampionshipResponseDto(admin.getChampionships())
				);
	}

	public static List<ChampionshipResponseDto> createChampionshipResponseDto(List<Championship> championships) {
		List<ChampionshipResponseDto> championshipDtos = new ArrayList<ChampionshipResponseDto>();
		for (Championship championship : championships) {
			championshipDtos.add(ChampionshipResponseDto.createChampionshipResponseDto(championship));
		}
		return championshipDtos;
	}
}
