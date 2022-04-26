package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@NotBlank(message = "Votre prénom Obligatoire")
	private String firstName;

	@NonNull
	@NotBlank(message = "Votre nom Obligatoire")
	private String lastName;

	@NotNull
	@NotBlank(message = "Votre nom Obligatoire")
	private String password;

	@Email
	private String emailAdress;

	@OneToMany(mappedBy = "admin")
	private List<Championship> championships;
}
