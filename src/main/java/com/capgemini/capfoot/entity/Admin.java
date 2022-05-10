package com.capgemini.capfoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
