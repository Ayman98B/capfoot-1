package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.*;
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
	@NotBlank(message = "Votre prénom et Obligatoire")
	private String firstName;

	@NonNull
	@NotBlank(message = "Votre nom et Obligatoire")
	private String lastName;

	@NotNull
	@NotBlank(message = "Votre nom Obligatoire")
	private String password;

	@Email
	@Column(unique = true)
	private String emailAdress;

	@OneToMany(mappedBy = "admin")
	private List<Championship> championships;
}
