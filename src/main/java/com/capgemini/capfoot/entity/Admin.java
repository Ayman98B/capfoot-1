package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

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
	@NotBlank(message = "Votre password Obligatoire")
	private String password;

	@Email
	@Column(unique = true)
	private String emailAdress;

	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	@ToString.Exclude
	private List<Championship> championships;
}
