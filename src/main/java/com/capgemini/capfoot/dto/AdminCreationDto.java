package com.capgemini.capfoot.dto;

import com.capgemini.capfoot.entity.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreationDto {

	private String firstName;

	private String lastName;

	private String password;

	private String emailAdress;
	
	
	public static AdminCreationDto createAdminResponseDto(Admin admin) {
		return new AdminCreationDto(
				admin.getFirstName(), 
				admin.getLastName(), 
				admin.getPassword(),
				admin.getEmailAdress()
				);
	}

}
